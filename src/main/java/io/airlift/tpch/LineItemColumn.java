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
import static io.airlift.tpch.TpchColumnTypes.IDENTIFIER;
import static io.airlift.tpch.TpchColumnTypes.DATE;
import static io.airlift.tpch.TpchColumnTypes.DOUBLE;
import static io.airlift.tpch.TpchColumnTypes.INTEGER;
import static io.airlift.tpch.TpchColumnTypes.varchar;

public enum LineItemColumn
        implements TpchColumn<LineItem>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("l_orderkey", IDENTIFIER)
            {
                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    PART_KEY("l_partkey", IDENTIFIER)
            {
                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getPartKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("l_suppkey", IDENTIFIER)
            {
                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getSupplierKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    LINE_NUMBER("l_linenumber", INTEGER)
            {
                public int getInteger(LineItem lineItem)
                {
                    return lineItem.getLineNumber();
                }
            },

    QUANTITY("l_quantity", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getQuantity();
                }

                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getQuantity() * 100;
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    EXTENDED_PRICE("l_extendedprice", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getExtendedPrice();
                }

                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getExtendedPriceInCents();
                }
            },

    DISCOUNT("l_discount", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getDiscount();
                }

                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getDiscountPercent();
                }
            },

    TAX("l_tax", DOUBLE)
            {
                public double getDouble(LineItem lineItem)
                {
                    return lineItem.getTax();
                }

                public long getIdentifier(LineItem lineItem)
                {
                    return lineItem.getTaxPercent();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    RETURN_FLAG("l_returnflag", varchar(1))
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getReturnFlag();
                }
            },

    STATUS("l_linestatus", varchar(1))
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getStatus();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_DATE("l_shipdate", DATE)
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
    COMMIT_DATE("l_commitdate", DATE)
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
    RECEIPT_DATE("l_receiptdate", DATE)
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
    SHIP_INSTRUCTIONS("l_shipinstruct", varchar(25))
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipInstructions();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_MODE("l_shipmode", varchar(10))
            {
                public String getString(LineItem lineItem)
                {
                    return lineItem.getShipMode();
                }
            },

    COMMENT("l_comment", varchar(44))
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
    public long getIdentifier(LineItem lineItem)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(LineItem lineItem)
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
