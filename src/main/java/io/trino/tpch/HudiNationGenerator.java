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

import com.google.common.collect.AbstractIterator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class HudiNationGenerator
        implements Iterable<HudiNation>
{
    private static final int COMMENT_AVERAGE_LENGTH = 72;

    private final Distributions distributions;
    private final TextPool textPool;
    private static final String HOODIE_INSTANT_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    public HudiNationGenerator()
    {
        this(Distributions.getDefaultDistributions(), TextPool.getDefaultTestPool());
    }

    public HudiNationGenerator(Distributions distributions, TextPool textPool)
    {
        this.distributions = requireNonNull(distributions, "distributions is null");
        this.textPool = requireNonNull(textPool, "textPool is null");
    }

    @Override
    public Iterator<HudiNation> iterator()
    {
        SimpleDateFormat instantTimeFormat = new SimpleDateFormat(HOODIE_INSTANT_TIME_FORMAT);
        Date timestamp = new Date();
        String hoodieInstantTime = instantTimeFormat.format(timestamp);
        return new HudiNationGeneratorIterator(distributions.getNations(), textPool, hoodieInstantTime);
    }

    private static class HudiNationGeneratorIterator
            extends AbstractIterator<HudiNation>
    {
        private final Distribution nations;
        private final RandomText commentRandom;

        private final String hoodieInstantTime;

        private int index;

        private HudiNationGeneratorIterator(Distribution nations, TextPool textPool, String hoodieInstantTime)
        {
            this.nations = nations;
            this.commentRandom = new RandomText(606179079, textPool, COMMENT_AVERAGE_LENGTH);
            this.hoodieInstantTime = hoodieInstantTime;
        }

        @Override
        protected HudiNation computeNext()
        {
            if (index >= nations.size()) {
                return endOfData();
            }
            HudiNation nation = new HudiNation(index,
                    index,
                    nations.getValue(index),
                    nations.getWeight(index),
                    commentRandom.nextValue(),
                    hoodieInstantTime);

            commentRandom.rowFinished();
            index++;

            return nation;
        }
    }
}
