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

import static io.airlift.tpch.GenerateUtils.formatDate;
import static io.airlift.tpch.TpchColumnType.BIGINT;
import static io.airlift.tpch.TpchColumnType.DATE;
import static io.airlift.tpch.TpchColumnType.DOUBLE;
import static io.airlift.tpch.TpchColumnType.VARCHAR;

public enum LineItemColumn
        implements TpchColumn<LineItem>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("orderkey", BIGINT)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    PART_KEY("partkey", BIGINT)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getPartKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("suppkey", BIGINT)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getSupplierKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    LINE_NUMBER("linenumber", BIGINT)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getLineNumber();
                }
            },

    QUANTITY("quantity", BIGINT)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getQuantity();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    EXTENDED_PRICE("extendedprice", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getExtendedPrice();
                }

                public long getLong(LineItem lingItem)
                {
                    return lingItem.getExtendedPriceInCents();
                }
            },

    DISCOUNT("discount", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getDiscount();
                }

                public long getLong(LineItem lineItem)
                {
                    return lineItem.getDiscountPercent();
                }
            },

    TAX("tax", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getTax();
                }

                public long getLong(LineItem lineItem)
                {
                    return lineItem.getTaxPercent();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    RETURN_FLAG("returnflag", VARCHAR)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getReturnFlag();
                }
            },

    STATUS("linestatus", VARCHAR)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getStatus();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_DATE("shipdate", DATE)
            {
                public String getString(LineItem lineItem)
                {
                    return formatDate(getDate(lineItem));
                }

                public int getDate(LineItem lineItem)
                {
                    return lineItem.getShipDate();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    COMMIT_DATE("commitdate", DATE)
            {
                public String getString(LineItem lineItem)
                {
                    return formatDate(getDate(lineItem));
                }

                public int getDate(LineItem lineItem)
                {
                    return lineItem.getCommitDate();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    RECEIPT_DATE("receiptdate", DATE)
            {
                public String getString(LineItem lineItem)
                {
                    return formatDate(getDate(lineItem));
                }

                @Override
                public int getDate(LineItem lineItem)
                {
                    return lineItem.getReceiptDate();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_INSTRUCTIONS("shipinstruct", VARCHAR)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipInstructions();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_MODE("shipmode", VARCHAR)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipMode();
                }
            },

    COMMENT("comment", VARCHAR)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getComment();
                }
            };


    private final String columnName;
    private final TpchColumnType type;

    LineItemColumn(String columnName, TpchColumnType type)
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
    public double getDouble(LineItem lineItem)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(LineItem lineItem)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(LineItem lineItem)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(LineItem entity)
    {
        throw new UnsupportedOperationException();
    }
}
