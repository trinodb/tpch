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

import static io.airlift.tpch.TpchColumnType.BIGINT;
import static io.airlift.tpch.TpchColumnType.DOUBLE;
import static io.airlift.tpch.TpchColumnType.VARCHAR;

public enum PartSupplierColumn
        implements TpchColumn<PartSupplier>
{
    @SuppressWarnings("SpellCheckingInspection")
    PART_KEY("partkey", BIGINT)
            {
                public long getLong(PartSupplier partSupplier)
                {
                    return partSupplier.getPartKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("suppkey", BIGINT)
            {
                public long getLong(PartSupplier partSupplier)
                {
                    return partSupplier.getSupplierKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    AVAILABLE_QUANTITY("availqty", BIGINT)
            {
                public long getLong(PartSupplier partSupplier)
                {
                    return partSupplier.getAvailableQuantity();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SUPPLY_COST("supplycost", DOUBLE)
            {
                public double getDouble(PartSupplier partSupplier)
                {
                    return partSupplier.getSupplyCost();
                }

                public long getLong(PartSupplier partSupplier)
                {
                    return partSupplier.getSupplyCostInCents();
                }
            },

    COMMENT("comment", VARCHAR)
            {
                public String getString(PartSupplier partSupplier)
                {
                    return partSupplier.getComment();
                }
            };


    private final String columnName;
    private final TpchColumnType type;

    PartSupplierColumn(String columnName, TpchColumnType type)
    {
        this.columnName = columnName;
        this.type = type;
    }

    @Override
    public String getColumnName()
    {
        return columnName;
    }

    @Override
    public TpchColumnType getType()
    {
        return type;
    }

    @Override
    public double getDouble(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(PartSupplier entity)
    {
        throw new UnsupportedOperationException();
    }
}
