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

public enum LineItemColumn
        implements TpchColumn<LineItem>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("orderkey", Long.class)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    PART_KEY("partkey", Long.class)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getPartKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("suppkey", Long.class)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getSupplierKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    LINE_NUMBER("linenumber", Long.class)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getLineNumber();
                }
            },

    QUANTITY("quantity", Long.class)
            {
                public long getLong(LineItem lineItem)
                {
                    return lineItem.getQuantity();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    EXTENDED_PRICE("extendedprice", Double.class)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getExtendedPrice();
                }
            },

    DISCOUNT("discount", Double.class)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getDiscount();
                }
            },

    TAX("tax", Double.class)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getTax();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    RETURN_FLAG("returnflag", String.class)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getReturnFlag();
                }
            },

    STATUS("linestatus", String.class)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getStatus();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_DATE("shipdate", Integer.class)
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
    COMMIT_DATE("commitdate", Integer.class)
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
    RECEIPT_DATE("receiptdate", Integer.class)
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
    SHIP_INSTRUCTIONS("shipinstruct", String.class)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipInstructions();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_MODE("shipmode", String.class)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipMode();
                }
            },

    COMMENT("comment", String.class)
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getComment();
                }
            };


    private final String columnName;
    private final Class<?> type;

    LineItemColumn(String columnName, Class<?> type)
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
    public Class<?> getType()
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
