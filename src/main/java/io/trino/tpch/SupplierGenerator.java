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

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static io.trino.tpch.GenerateUtils.calculateRowCount;
import static io.trino.tpch.GenerateUtils.calculateStartIndex;
import static io.trino.tpch.StringUtils.padWithZeros;
import static java.util.Objects.requireNonNull;

public class SupplierGenerator
        implements Iterable<Supplier>
{
    public static final int SCALE_BASE = 10_000;

    private static final int ACCOUNT_BALANCE_MIN = -99999;
    private static final int ACCOUNT_BALANCE_MAX = 999999;
    private static final int ADDRESS_AVERAGE_LENGTH = 25;
    private static final int COMMENT_AVERAGE_LENGTH = 63;

    public static final String BBB_BASE_TEXT = "Customer ";
    public static final String BBB_COMPLAINT_TEXT = "Complaints";
    public static final String BBB_RECOMMEND_TEXT = "Recommends";
    public static final int BBB_COMMENT_LENGTH = BBB_BASE_TEXT.length() + BBB_COMPLAINT_TEXT.length();
    public static final int BBB_COMMENTS_PER_SCALE_BASE = 10;
    public static final int BBB_COMPLAINT_PERCENT = 50;

    private final double scaleFactor;
    private final int part;
    private final int partCount;

    private final Distributions distributions;
    private final java.util.function.Supplier<TextPool> textPool;

    public SupplierGenerator(double scaleFactor, int part, int partCount)
    {
        this(scaleFactor, part, partCount, Distributions.getDefaultDistributions(), TextPool.getDefaultTextPool());
    }

    public SupplierGenerator(double scaleFactor, int part, int partCount, Distributions distributions, java.util.function.Supplier<TextPool> textPool)
    {
        checkArgument(scaleFactor > 0, "scaleFactor must be greater than 0");
        checkArgument(part >= 1, "part must be at least 1");
        checkArgument(part <= partCount, "part must be less than or equal to part count");

        this.scaleFactor = scaleFactor;
        this.part = part;
        this.partCount = partCount;

        this.distributions = requireNonNull(distributions, "distributions is null");
        this.textPool = requireNonNull(textPool, "textPool is null");
    }

    @Override
    public Iterator<Supplier> iterator()
    {
        return new SupplierGeneratorIterator(
                distributions,
                textPool.get(),
                calculateStartIndex(SCALE_BASE, scaleFactor, part, partCount),
                calculateRowCount(SCALE_BASE, scaleFactor, part, partCount));
    }

    private static class SupplierGeneratorIterator
            extends AbstractIterator<Supplier>
    {
        private final RandomAlphaNumeric addressRandom = new RandomAlphaNumeric(706178559, ADDRESS_AVERAGE_LENGTH);
        private final RandomBoundedInt nationKeyRandom;
        private final RandomPhoneNumber phoneRandom = new RandomPhoneNumber(884434366);
        private final RandomBoundedInt accountBalanceRandom = new RandomBoundedInt(962338209, ACCOUNT_BALANCE_MIN, ACCOUNT_BALANCE_MAX);
        private final RandomText commentRandom;
        private final RandomBoundedInt bbbCommentRandom = new RandomBoundedInt(202794285, 1, SCALE_BASE);
        private final RandomInt bbbJunkRandom = new RandomInt(263032577, 1);
        private final RandomInt bbbOffsetRandom = new RandomInt(715851524, 1);
        private final RandomBoundedInt bbbTypeRandom = new RandomBoundedInt(753643799, 0, 100);

        private final long startIndex;
        private final long rowCount;

        private long index;

        private SupplierGeneratorIterator(Distributions distributions, TextPool textPool, long startIndex, long rowCount)
        {
            this.startIndex = startIndex;
            this.rowCount = rowCount;

            nationKeyRandom = new RandomBoundedInt(110356601, 0, distributions.getNations().size() - 1);
            commentRandom = new RandomText(1341315363, textPool, COMMENT_AVERAGE_LENGTH);

            addressRandom.advanceRows(startIndex);
            nationKeyRandom.advanceRows(startIndex);
            phoneRandom.advanceRows(startIndex);
            accountBalanceRandom.advanceRows(startIndex);
            commentRandom.advanceRows(startIndex);
            bbbCommentRandom.advanceRows(startIndex);
            bbbJunkRandom.advanceRows(startIndex);
            bbbOffsetRandom.advanceRows(startIndex);
            bbbTypeRandom.advanceRows(startIndex);
        }

        @Override
        protected Supplier computeNext()
        {
            if (index >= rowCount) {
                return endOfData();
            }

            Supplier supplier = makeSupplier(startIndex + index + 1);

            addressRandom.rowFinished();
            nationKeyRandom.rowFinished();
            phoneRandom.rowFinished();
            accountBalanceRandom.rowFinished();
            commentRandom.rowFinished();
            bbbCommentRandom.rowFinished();
            bbbJunkRandom.rowFinished();
            bbbOffsetRandom.rowFinished();
            bbbTypeRandom.rowFinished();

            index++;

            return supplier;
        }

        private Supplier makeSupplier(long supplierKey)
        {
            String comment = commentRandom.nextValue();

            // Add supplier complaints or commendation to the comment
            int bbbCommentRandomValue = bbbCommentRandom.nextValue();
            if (bbbCommentRandomValue <= BBB_COMMENTS_PER_SCALE_BASE) {
                StringBuilder buffer = new StringBuilder(comment);

                // select random place for BBB comment
                int noise = bbbJunkRandom.nextInt(0, (comment.length() - BBB_COMMENT_LENGTH));
                int offset = bbbOffsetRandom.nextInt(0, (comment.length() - (BBB_COMMENT_LENGTH + noise)));

                // select complaint or recommendation
                String type;
                if (bbbTypeRandom.nextValue() < BBB_COMPLAINT_PERCENT) {
                    type = BBB_COMPLAINT_TEXT;
                }
                else {
                    type = BBB_RECOMMEND_TEXT;
                }

                // write base text (e.g., "Customer ")
                buffer.replace(offset, offset + BBB_BASE_TEXT.length(), BBB_BASE_TEXT);

                // write complaint or commendation text (e.g., "Complaints" or "Recommends")
                buffer.replace(
                        BBB_BASE_TEXT.length() + offset + noise,
                        BBB_BASE_TEXT.length() + offset + noise + type.length(),
                        type);

                comment = buffer.toString();
            }

            long nationKey = nationKeyRandom.nextValue();

            return new Supplier(supplierKey,
                    supplierKey,
                    "Supplier#" + padWithZeros(supplierKey, 9),
                    addressRandom.nextValue(),
                    nationKey,
                    phoneRandom.nextValue(nationKey),
                    accountBalanceRandom.nextValue(),
                    comment);
        }
    }
}
