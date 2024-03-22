/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.tpch;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
import static io.trino.tpch.Distributions.getDefaultDistributions;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TextPool
{
    private static final Logger log = Logger.getLogger(TextPool.class.getName());

    private static final int DEFAULT_TEXT_POOL_SIZE = 300 * 1024 * 1024;
    private static final int MAX_SENTENCE_LENGTH = 256;

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor(
            new ThreadFactoryBuilder()
                    .setDaemon(true)
                    .setNameFormat(TextPool.class.getName() + "-%s")
                    .build());

    private static final Supplier<TextPool> DEFAULT_TEXT_POOL = new SharingTextPoolSupplier(EXECUTOR, TextPool::createDefaultTextPool);

    public static Supplier<TextPool> getDefaultTextPool()
    {
        return DEFAULT_TEXT_POOL;
    }

    private static TextPool createDefaultTextPool()
    {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TextPool pool = new TextPool(DEFAULT_TEXT_POOL_SIZE, getDefaultDistributions());
        log.fine(() -> format("Created new TextPool in %s ms", stopwatch.elapsed(MILLISECONDS)));
        return pool;
    }

    private final byte[] textPool;
    private final int textPoolSize;

    public TextPool(int size, Distributions distributions)
    {
        this(size, distributions, progress -> {});
    }

    public TextPool(int size, Distributions distributions, TextGenerationProgressMonitor monitor)
    {
        requireNonNull(distributions, "distributions is null");
        requireNonNull(monitor, "monitor is null");

        ByteArrayBuilder output = new ByteArrayBuilder(size + MAX_SENTENCE_LENGTH);

        RandomInt randomInt = new RandomInt(933588178, Integer.MAX_VALUE);

        while (output.getLength() < size) {
            generateSentence(distributions, output, randomInt);
            monitor.updateProgress(Math.min(1.0 * output.getLength() / size, 1.0));
        }
        output.erase(output.getLength() - size);
        textPool = output.getBytes();
        textPoolSize = output.getLength();
    }

    public int size()
    {
        return textPoolSize;
    }

    public String getText(int begin, int end)
    {
        if (end > textPoolSize) {
            throw new IndexOutOfBoundsException(format("Index %d is beyond end of text pool (size = %d)", end, textPoolSize));
        }
        return new String(textPool, begin, end - begin, US_ASCII);
    }

    private static void generateSentence(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getGrammars().randomValue(random);

        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i += 2) {
            switch (syntax.charAt(i)) {
                case 'V' -> generateVerbPhrase(distributions, builder, random);
                case 'N' -> generateNounPhrase(distributions, builder, random);
                case 'P' -> {
                    String preposition = distributions.getPrepositions().randomValue(random);
                    builder.append(preposition);
                    builder.append(" the ");
                    generateNounPhrase(distributions, builder, random);
                }
                case 'T' -> {
                    // trim trailing space
                    // terminators should abut previous word
                    builder.erase(1);
                    String terminator = distributions.getTerminators().randomValue(random);
                    builder.append(terminator);
                }
                default -> throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            }
            if (builder.getLastChar() != ' ') {
                builder.append(" ");
            }
        }
    }

    private static void generateVerbPhrase(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getVerbPhrase().randomValue(random);
        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i += 2) {
            Distribution source = switch (syntax.charAt(i)) {
                case 'D' -> distributions.getAdverbs();
                case 'V' -> distributions.getVerbs();
                case 'X' -> distributions.getAuxiliaries();
                default -> throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            };

            // pick a random word
            String word = source.randomValue(random);
            builder.append(word);

            // add a space
            builder.append(" ");
        }
    }

    private static void generateNounPhrase(Distributions distributions, ByteArrayBuilder builder, RandomInt random)
    {
        String syntax = distributions.getNounPhrase().randomValue(random);
        int maxLength = syntax.length();
        for (int i = 0; i < maxLength; i++) {
            Distribution source = switch (syntax.charAt(i)) {
                case 'A' -> distributions.getArticles();
                case 'J' -> distributions.getAdjectives();
                case 'D' -> distributions.getAdverbs();
                case 'N' -> distributions.getNouns();
                case ',' -> {
                    builder.erase(1);
                    builder.append(", ");
                    yield null;
                }
                case ' ' -> null;
                default -> throw new IllegalArgumentException("Unknown token '" + syntax.charAt(i) + "'");
            };

            if (source == null) {
                continue;
            }

            // pick a random word
            builder.append(source.randomValue(random));
            // add a space
            builder.append(" ");
        }
    }

    public interface TextGenerationProgressMonitor
    {
        void updateProgress(double progress);
    }

    private static class ByteArrayBuilder
    {
        private int length;
        private final byte[] bytes;

        public ByteArrayBuilder(int size)
        {
            this.bytes = new byte[size];
        }

        @SuppressWarnings("deprecation")
        public void append(String string)
        {
            // This is safe because the data is ASCII
            string.getBytes(0, string.length(), bytes, length);
            length += string.length();
        }

        public void erase(int count)
        {
            checkState(length >= count, "not enough bytes to erase");
            length -= count;
        }

        public int getLength()
        {
            return length;
        }

        public byte[] getBytes()
        {
            return bytes;
        }

        public char getLastChar()
        {
            return (char) bytes[length - 1];
        }
    }
}
