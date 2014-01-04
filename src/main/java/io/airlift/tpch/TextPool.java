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
package io.airlift.tpch;

import com.google.common.base.Splitter;

import static com.google.common.base.CharMatcher.WHITESPACE;

public class TextPool
{
    private static final int DEFAULT_TEXT_POOL_SIZE = 300 * 1024 * 1024;
    private static final int MAX_SENTENCE_LENGTH = 256;

    private static TextPool DEFAULT_TEXT_POOL;

    public static synchronized TextPool getDefaultTestPool()
    {
        if (DEFAULT_TEXT_POOL == null) {
            DEFAULT_TEXT_POOL = new TextPool(DEFAULT_TEXT_POOL_SIZE, Distributions.getDefaultDistributions());
        }
        return DEFAULT_TEXT_POOL;
    }

    private final String textPool;

    public TextPool(int size, Distributions distributions)
    {
        this(size, distributions, new TextGenerationProgressMonitor()
        {
            public void updateProgress(double progress)
            {
            }
        });
    }

    public TextPool(int size, Distributions distributions, TextGenerationProgressMonitor monitor)
    {
        StringBuilder output = new StringBuilder(size + MAX_SENTENCE_LENGTH);

        RandomInt randomInt = new RandomInt(933588178, Integer.MAX_VALUE);

        while (output.length() < size) {
            generateSentence(distributions, output, randomInt);
            monitor.updateProgress(Math.min(1.0 * output.length() / size, 1.0));
        }
        output.setLength(size);
        textPool = output.toString();
    }

    public int size()
    {
        return textPool.length();
    }

    public String getText(int offset, int length)
    {
        return textPool.substring(offset, length);
    }

    private static void generateSentence(Distributions distributions, StringBuilder builder, RandomInt random)
    {
        String syntax = distributions.getGrammars().randomValue(random);

        for (String token : Splitter.on(WHITESPACE).split(syntax)) {
            switch (token) {
                case "V":
                    generateVerbPhrase(distributions, builder, random);
                    break;
                case "N":
                    generateNounPhrase(distributions, builder, random);
                    break;
                case "P":
                    String preposition = distributions.getPrepositions().randomValue(random);
                    builder.append(preposition);
                    builder.append(" the ");
                    generateNounPhrase(distributions, builder, random);
                    break;
                case "T":
                    // trim trailing space
                    // terminators should abut previous word
                    builder.setLength(builder.length() - 1);
                    String terminator = distributions.getTerminators().randomValue(random);
                    builder.append(terminator);
                    break;
                default:
                    throw new IllegalStateException("Unknown token '" + token + "'");
            }
            if (builder.charAt(builder.length() - 1) != ' ') {
                builder.append(' ');
            }
        }
    }

    private static void generateVerbPhrase(Distributions distributions, StringBuilder builder, RandomInt random)
    {
        String syntax = distributions.getVerbPhrase().randomValue(random);
        for (String token : Splitter.on(' ').split(syntax)) {
            Distribution source;
            switch (token) {
                case "D":
                    source = distributions.getAdverbs();
                    break;
                case "V":
                    source = distributions.getVerbs();
                    break;
                case "X":
                    source = distributions.getAuxiliaries();
                    break;
                default:
                    throw new IllegalStateException("Unknown token '" + token + "'");
            }

            // pick a random word
            String word = source.randomValue(random);
            builder.append(word);

            // string may end with a comma or such
            builder.append(token.substring(1));

            // add a space
            builder.append(" ");
        }
    }

    private static void generateNounPhrase(Distributions distributions, StringBuilder builder, RandomInt random)
    {
        String syntax = distributions.getNounPhrase().randomValue(random);
        for (String token : Splitter.on(' ').split(syntax)) {
            Distribution source;
            switch (token.charAt(0)) {
                case 'A':
                    source = distributions.getArticles();
                    break;
                case 'J':
                    source = distributions.getAdjectives();
                    break;
                case 'D':
                    source = distributions.getAdverbs();
                    break;
                case 'N':
                    source = distributions.getNouns();
                    break;
                default:
                    throw new IllegalStateException("Unknown token '" + token + "'");
            }
            // pick a random word
            String word = source.randomValue(random);
            builder.append(word);

            // string may end with a comma or such
            builder.append(token.substring(1));

            // add a space
            builder.append(" ");
        }
    }

    public interface TextGenerationProgressMonitor
    {
        void updateProgress(double progress);
    }
}
