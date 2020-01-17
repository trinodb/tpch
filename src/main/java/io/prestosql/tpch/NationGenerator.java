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
package io.prestosql.tpch;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class NationGenerator
        implements Iterable<Nation>
{
    private static final int COMMENT_AVERAGE_LENGTH = 72;

    private final Distributions distributions;
    private final TextPool textPool;

    public NationGenerator()
    {
        this(Distributions.getDefaultDistributions(), TextPool.getDefaultTestPool());
    }

    public NationGenerator(Distributions distributions, TextPool textPool)
    {
        this.distributions = requireNonNull(distributions, "distributions is null");
        this.textPool = requireNonNull(textPool, "textPool is null");
    }

    @Override
    public Iterator<Nation> iterator()
    {
        return new NationGeneratorIterator(distributions.getNations(), textPool);
    }

    private static class NationGeneratorIterator
            extends AbstractIterator<Nation>
    {
        private final Distribution nations;
        private final RandomText commentRandom;

        private int index;

        private NationGeneratorIterator(Distribution nations, TextPool textPool)
        {
            this.nations = nations;
            this.commentRandom = new RandomText(606179079, textPool, COMMENT_AVERAGE_LENGTH);
        }

        @Override
        protected Nation computeNext()
        {
            if (index >= nations.size()) {
                return endOfData();
            }

            Nation nation = new Nation(index,
                    index,
                    nations.getValue(index),
                    nations.getWeight(index),
                    commentRandom.nextValue());

            commentRandom.rowFinished();
            index++;

            return nation;
        }
    }
}
