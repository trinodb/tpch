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
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkArgument;
import static io.trino.tpch.GenerateUtils.calculateRowCount;
import static io.trino.tpch.GenerateUtils.calculateStartIndex;
import static io.trino.tpch.GenerateUtils.toEpochDate;
import static io.trino.tpch.OrderGenerator.LINE_COUNT_MAX;
import static io.trino.tpch.OrderGenerator.createLineCountRandom;
import static io.trino.tpch.OrderGenerator.createOrderDateRandom;
import static io.trino.tpch.OrderGenerator.makeOrderKey;
import static io.trino.tpch.PartSupplierGenerator.selectPartSupplier;
import static java.util.Objects.requireNonNull;

public class LineItemGenerator
        implements Iterable<LineItem>
{
    private static final int QUANTITY_MIN = 1;
    private static final int QUANTITY_MAX = 50;
    private static final int TAX_MIN = 0;
    private static final int TAX_MAX = 8;
    private static final int DISCOUNT_MIN = 0;
    private static final int DISCOUNT_MAX = 10;
    private static final int PART_KEY_MIN = 1;

    private static final int SHIP_DATE_MIN = 1;
    private static final int SHIP_DATE_MAX = 121;
    private static final int COMMIT_DATE_MIN = 30;
    private static final int COMMIT_DATE_MAX = 90;
    private static final int RECEIPT_DATE_MIN = 1;
    private static final int RECEIPT_DATE_MAX = 30;

    static final int ITEM_SHIP_DAYS = SHIP_DATE_MAX + RECEIPT_DATE_MAX;

    private static final int COMMENT_AVERAGE_LENGTH = 27;

    private final double scaleFactor;
    private final int part;
    private final int partCount;

    private final Distributions distributions;
    private final Supplier<TextPool> textPool;

    public LineItemGenerator(double scaleFactor, int part, int partCount)
    {
        this(scaleFactor, part, partCount, Distributions.getDefaultDistributions(), TextPool.getDefaultTextPool());
    }

    public LineItemGenerator(double scaleFactor, int part, int partCount, Distributions distributions, Supplier<TextPool> textPool)
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
    public Iterator<LineItem> iterator()
    {
        return new LineItemGeneratorIterator(
                distributions,
                textPool.get(),
                scaleFactor,
                calculateStartIndex(OrderGenerator.SCALE_BASE, scaleFactor, part, partCount),
                calculateRowCount(OrderGenerator.SCALE_BASE, scaleFactor, part, partCount));
    }

    private static class LineItemGeneratorIterator
            extends AbstractIterator<LineItem>
    {
        private final RandomBoundedInt orderDateRandom = createOrderDateRandom();
        private final RandomBoundedInt lineCountRandom = createLineCountRandom();

        private final RandomBoundedInt quantityRandom = createQuantityRandom();
        private final RandomBoundedInt discountRandom = createDiscountRandom();
        private final RandomBoundedInt taxRandom = createTaxRandom();

        private final RandomBoundedLong linePartKeyRandom;

        private final RandomBoundedInt supplierNumberRandom = new RandomBoundedInt(2095021727, 0, 3, LINE_COUNT_MAX);

        private final RandomBoundedInt shipDateRandom = createShipDateRandom();
        private final RandomBoundedInt commitDateRandom = new RandomBoundedInt(904914315, COMMIT_DATE_MIN, COMMIT_DATE_MAX, LINE_COUNT_MAX);
        private final RandomBoundedInt receiptDateRandom = new RandomBoundedInt(373135028, RECEIPT_DATE_MIN, RECEIPT_DATE_MAX, LINE_COUNT_MAX);

        private final RandomString returnedFlagRandom;
        private final RandomString shipInstructionsRandom;
        private final RandomString shipModeRandom;

        private final RandomText commentRandom;

        private final double scaleFactor;
        private final long startIndex;

        private final long rowCount;

        private long index;
        private int orderDate;
        private int lineCount;
        private int lineNumber;

        private LineItemGeneratorIterator(Distributions distributions, TextPool textPool, double scaleFactor, long startIndex, long rowCount)
        {
            this.scaleFactor = scaleFactor;
            this.startIndex = startIndex;
            this.rowCount = rowCount;

            returnedFlagRandom = new RandomString(717419739, distributions.getReturnFlags(), LINE_COUNT_MAX);
            shipInstructionsRandom = new RandomString(1371272478, distributions.getShipInstructions(), LINE_COUNT_MAX);
            shipModeRandom = new RandomString(675466456, distributions.getShipModes(), LINE_COUNT_MAX);
            commentRandom = new RandomText(1095462486, textPool, COMMENT_AVERAGE_LENGTH, LINE_COUNT_MAX);

            linePartKeyRandom = createPartKeyRandom(scaleFactor);

            orderDateRandom.advanceRows(startIndex);
            lineCountRandom.advanceRows(startIndex);

            quantityRandom.advanceRows(startIndex);
            discountRandom.advanceRows(startIndex);
            taxRandom.advanceRows(startIndex);

            linePartKeyRandom.advanceRows(startIndex);

            supplierNumberRandom.advanceRows(startIndex);

            shipDateRandom.advanceRows(startIndex);
            commitDateRandom.advanceRows(startIndex);
            receiptDateRandom.advanceRows(startIndex);

            returnedFlagRandom.advanceRows(startIndex);
            shipInstructionsRandom.advanceRows(startIndex);
            shipModeRandom.advanceRows(startIndex);

            commentRandom.advanceRows(startIndex);

            // generate information for initial order
            orderDate = orderDateRandom.nextValue();
            lineCount = lineCountRandom.nextValue() - 1;
        }

        @Override
        protected LineItem computeNext()
        {
            if (index >= rowCount) {
                return endOfData();
            }

            LineItem lineitem = makeLineitem(startIndex + index + 1);
            lineNumber++;

            // advance next row only when all lines for the order have been produced
            if (lineNumber > lineCount) {
                orderDateRandom.rowFinished();

                lineCountRandom.rowFinished();
                quantityRandom.rowFinished();
                discountRandom.rowFinished();
                taxRandom.rowFinished();

                linePartKeyRandom.rowFinished();

                supplierNumberRandom.rowFinished();

                shipDateRandom.rowFinished();
                commitDateRandom.rowFinished();
                receiptDateRandom.rowFinished();

                returnedFlagRandom.rowFinished();
                shipInstructionsRandom.rowFinished();
                shipModeRandom.rowFinished();

                commentRandom.rowFinished();

                index++;

                // generate information for next order
                lineCount = lineCountRandom.nextValue() - 1;
                orderDate = orderDateRandom.nextValue();
                lineNumber = 0;
            }

            return lineitem;
        }

        private LineItem makeLineitem(long orderIndex)
        {
            long orderKey = makeOrderKey(orderIndex);

            int quantity = quantityRandom.nextValue();
            int discount = discountRandom.nextValue();
            int tax = taxRandom.nextValue();

            long partKey = linePartKeyRandom.nextValue();

            int supplierNumber = supplierNumberRandom.nextValue();
            long supplierKey = selectPartSupplier(partKey, supplierNumber, scaleFactor);

            long partPrice = PartGenerator.calculatePartPrice(partKey);
            long extendedPrice = partPrice * quantity;

            int shipDate = shipDateRandom.nextValue();
            shipDate += orderDate;
            int commitDate = commitDateRandom.nextValue();
            commitDate += orderDate;
            int receiptDate = receiptDateRandom.nextValue();
            receiptDate += shipDate;

            String returnedFlag;
            if (GenerateUtils.isInPast(receiptDate)) {
                returnedFlag = returnedFlagRandom.nextValue();
            }
            else {
                returnedFlag = "N";
            }

            String status;
            if (GenerateUtils.isInPast(shipDate)) {
                status = "F";
            }
            else {
                status = "O";
            }

            String shipInstructions = shipInstructionsRandom.nextValue();
            String shipMode = shipModeRandom.nextValue();
            String comment = commentRandom.nextValue();

            return new LineItem(orderIndex,
                    orderKey,
                    partKey,
                    supplierKey,
                    lineNumber + 1,
                    quantity,
                    extendedPrice,
                    discount,
                    tax,
                    returnedFlag,
                    status,
                    toEpochDate(shipDate),
                    toEpochDate(commitDate),
                    toEpochDate(receiptDate),
                    shipInstructions,
                    shipMode,
                    comment);
        }
    }

    static RandomBoundedInt createQuantityRandom()
    {
        return new RandomBoundedInt(209208115, QUANTITY_MIN, QUANTITY_MAX, LINE_COUNT_MAX);
    }

    static RandomBoundedInt createDiscountRandom()
    {
        return new RandomBoundedInt(554590007, DISCOUNT_MIN, DISCOUNT_MAX, LINE_COUNT_MAX);
    }

    static RandomBoundedInt createTaxRandom()
    {
        return new RandomBoundedInt(721958466, TAX_MIN, TAX_MAX, LINE_COUNT_MAX);
    }

    static RandomBoundedLong createPartKeyRandom(double scaleFactor)
    {
        return new RandomBoundedLong(1808217256, scaleFactor >= 30000, PART_KEY_MIN, (long) (PartGenerator.SCALE_BASE * scaleFactor), LINE_COUNT_MAX);
    }

    static RandomBoundedInt createShipDateRandom()
    {
        return new RandomBoundedInt(1769349045, SHIP_DATE_MIN, SHIP_DATE_MAX, LINE_COUNT_MAX);
    }
}
