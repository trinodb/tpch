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

import static io.trino.tpch.GenerateUtils.formatMoney;
import static io.trino.tpch.StringUtils.buildLine;
import static java.util.Objects.requireNonNull;

public class PartSupplier
        implements TpchEntity
{
    private final long rowNumber;
    private final long partKey;
    private final long supplierKey;
    private final int availableQuantity;
    private final long supplyCost;
    private final String comment;

    public PartSupplier(long rowNumber, long partKey, long supplierKey, int availableQuantity, long supplyCost, String comment)
    {
        this.rowNumber = rowNumber;
        this.partKey = partKey;
        this.supplierKey = supplierKey;
        this.availableQuantity = availableQuantity;
        this.supplyCost = supplyCost;
        this.comment = requireNonNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
    }

    public long getPartKey()
    {
        return partKey;
    }

    public long getSupplierKey()
    {
        return supplierKey;
    }

    public int getAvailableQuantity()
    {
        return availableQuantity;
    }

    public double getSupplyCost()
    {
        return supplyCost / 100.0;
    }

    public long getSupplyCostInCents()
    {
        return supplyCost;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return buildLine(partKey, supplierKey, availableQuantity, formatMoney(supplyCost), comment);
    }
}
