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

public enum PartColumn
        implements TpchColumn<Part>
{
    @SuppressWarnings("SpellCheckingInspection")
    PART_KEY("partkey", BIGINT)
            {
                public long getLong(Part part)
                {
                    return part.getPartKey();
                }
            },

    NAME("name", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getName();
                }
            },

    MANUFACTURER("mfgr", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getManufacturer();
                }
            },

    BRAND("brand", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getBrand();
                }
            },

    TYPE("type", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getType();
                }
            },

    SIZE("size", BIGINT)
            {
                public long getLong(Part part)
                {
                    return part.getSize();
                }
            },

    CONTAINER("container", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getContainer();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    RETAIL_PRICE("retailprice", DOUBLE)
            {
                public double getDouble(Part part)
                {
                    return part.getRetailPrice();
                }

                public long getLong(Part part)
                {
                    return part.getRetailPriceInCents();
                }
            },

    COMMENT("comment", VARCHAR)
            {
                public String getString(Part part)
                {
                    return part.getComment();
                }
            };

    private final String columnName;
    private final TpchColumnType type;

    PartColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Part entity)
    {
        throw new UnsupportedOperationException();
    }
}
