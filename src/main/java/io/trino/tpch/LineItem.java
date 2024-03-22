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

public record LineItem(
        long rowNumber,
        long orderKey,
        long partKey,
        long supplierKey,
        int lineNumber,
        long quantity,
        long extendedPriceInCents,
        long discountPercent,
        long taxPercent,
        String returnFlag,
        String status,
        int shipDate,
        int commitDate,
        int receiptDate,
        String shipInstructions,
        String shipMode,
        String comment)
        implements TpchEntity
{
    public LineItem
    {
        requireNonNull(returnFlag, "returnFlag is null");
        requireNonNull(status, "status is null");
        requireNonNull(shipInstructions, "shipInstructions is null");
        requireNonNull(shipMode, "shipMode is null");
        requireNonNull(comment, "comment is null");
    }

    public double extendedPrice()
    {
        return extendedPriceInCents / 100.0;
    }

    public double discount()
    {
        return discountPercent / 100.0;
    }

    public double tax()
    {
        return taxPercent / 100.0;
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
                formatMoney(extendedPriceInCents),
                formatMoney(discountPercent),
                formatMoney(taxPercent),
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
