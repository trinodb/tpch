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

import static io.trino.tpch.GenerateUtils.formatDate;
import static io.trino.tpch.GenerateUtils.formatMoney;
import static io.trino.tpch.StringUtils.buildLine;
import static java.util.Objects.requireNonNull;

public class LineItem
        implements TpchEntity
{
    private final long rowNumber;
    private final long orderKey;
    private final long partKey;
    private final long supplierKey;
    private final int lineNumber;
    private final long quantity;
    private final long extendedPrice;
    private final long discount;
    private final long tax;
    private final String returnFlag;
    private final String status;
    private final int shipDate;
    private final int commitDate;
    private final int receiptDate;
    private final String shipInstructions;
    private final String shipMode;
    private final String comment;

    public LineItem(long rowNumber,
            long orderKey,
            long partKey,
            long supplierKey,
            int lineNumber,
            long quantity,
            long extendedPrice,
            long discount,
            long tax,
            String returnFlag,
            String status,
            int shipDate,
            int commitDate,
            int receiptDate,
            String shipInstructions,
            String shipMode,
            String comment)
    {
        this.rowNumber = rowNumber;
        this.orderKey = orderKey;
        this.partKey = partKey;
        this.supplierKey = supplierKey;
        this.lineNumber = lineNumber;
        this.quantity = quantity;
        this.extendedPrice = extendedPrice;
        this.discount = discount;
        this.tax = tax;
        this.returnFlag = requireNonNull(returnFlag, "returnFlag is null");
        this.status = requireNonNull(status, "status is null");
        this.shipDate = shipDate;
        this.commitDate = commitDate;
        this.receiptDate = receiptDate;
        this.shipInstructions = requireNonNull(shipInstructions, "shipInstructions is null");
        this.shipMode = requireNonNull(shipMode, "shipMode is null");
        this.comment = requireNonNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
    }

    public long getOrderKey()
    {
        return orderKey;
    }

    public long getPartKey()
    {
        return partKey;
    }

    public long getSupplierKey()
    {
        return supplierKey;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public double getExtendedPrice()
    {
        return extendedPrice / 100.0;
    }

    public long getExtendedPriceInCents()
    {
        return extendedPrice;
    }

    public double getDiscount()
    {
        return discount / 100.0;
    }

    public long getDiscountPercent()
    {
        return discount;
    }

    public double getTax()
    {
        return tax / 100.0;
    }

    public long getTaxPercent()
    {
        return tax;
    }

    public String getReturnFlag()
    {
        return returnFlag;
    }

    public String getStatus()
    {
        return status;
    }

    public int getShipDate()
    {
        return shipDate;
    }

    public int getCommitDate()
    {
        return commitDate;
    }

    public int getReceiptDate()
    {
        return receiptDate;
    }

    public String getShipInstructions()
    {
        return shipInstructions;
    }

    public String getShipMode()
    {
        return shipMode;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return buildLine(
                orderKey,
                partKey,
                supplierKey,
                lineNumber,
                quantity,
                formatMoney(extendedPrice),
                formatMoney(discount),
                formatMoney(tax),
                returnFlag,
                status,
                formatDate(shipDate),
                formatDate(commitDate),
                formatDate(receiptDate),
                shipInstructions,
                shipMode,
                comment);
    }
}
