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

import static com.google.common.base.Preconditions.checkNotNull;
import static io.airlift.tpch.GenerateUtils.formatMoney;
import static java.util.Locale.ENGLISH;

public class LineItem
        implements TpchEntity
{
    private final long orderKey;
    private final long partKey;
    private final long supplierKey;
    private final long lineNumber;
    private final long quantity;
    private final long extendedPrice;
    private final long discount;
    private final long tax;
    private final String returnFlag;
    private final String status;
    private final String shipDate;
    private final String commitDate;
    private final String receiptDate;
    private final String shipInstructions;
    private final String shipMode;
    private final String comment;

    public LineItem(long orderKey,
            long partKey,
            long supplierKey,
            long lineNumber,
            long quantity,
            long extendedPrice,
            long discount,
            long tax,
            String returnFlag,
            String status,
            String shipDate,
            String commitDate,
            String receiptDate,
            String shipInstructions,
            String shipMode,
            String comment)
    {
        this.orderKey = orderKey;
        this.partKey = partKey;
        this.supplierKey = supplierKey;
        this.lineNumber = lineNumber;
        this.quantity = quantity;
        this.extendedPrice = extendedPrice;
        this.discount = discount;
        this.tax = tax;
        this.returnFlag = checkNotNull(returnFlag, "returnFlag is null");
        this.status = checkNotNull(status, "status is null");
        this.shipDate = checkNotNull(shipDate, "shipDate is null");
        this.commitDate = checkNotNull(commitDate, "commitDate is null");
        this.receiptDate = checkNotNull(receiptDate, "receiptDate is null");
        this.shipInstructions = checkNotNull(shipInstructions, "shipInstructions is null");
        this.shipMode = checkNotNull(shipMode, "shipMode is null");
        this.comment = checkNotNull(comment, "comment is null");
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

    public long getLineNumber()
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

    public double getDiscount()
    {
        return discount / 100.0;
    }

    public double getTax()
    {
        return tax / 100.0;
    }

    public String getReturnFlag()
    {
        return returnFlag;
    }

    public String getStatus()
    {
        return status;
    }

    public String getShipDate()
    {
        return shipDate;
    }

    public String getCommitDate()
    {
        return commitDate;
    }

    public String getReceiptDate()
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

    public String toLine()
    {
        return String.format(ENGLISH,
                "%d|%d|%d|%d|%d|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|",
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
                shipDate,
                commitDate,
                receiptDate,
                shipInstructions,
                shipMode,
                comment);
    }
}
