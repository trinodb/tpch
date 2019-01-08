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
import static io.airlift.tpch.TpchColumnTypes.DATE;
import static io.airlift.tpch.TpchColumnTypes.DOUBLE;
import static io.airlift.tpch.TpchColumnTypes.IDENTIFIER;
import static io.airlift.tpch.TpchColumnTypes.INTEGER;
import static io.airlift.tpch.TpchColumnTypes.varchar;

public enum LineItemColumn
        implements TpchColumn<LineItem>
{
    ORDER_KEY("l_orderkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getOrderKey();
        }
    },

    PART_KEY("l_partkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getPartKey();
        }
    },

    SUPPLIER_KEY("l_suppkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getSupplierKey();
        }
    },

    LINE_NUMBER("l_linenumber", INTEGER) {
        @Override
        public int getInteger(LineItem lineItem)
        {
            return lineItem.getLineNumber();
        }
    },

    QUANTITY("l_quantity", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.getQuantity();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getQuantity() * 100;
        }
    },

    EXTENDED_PRICE("l_extendedprice", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.getExtendedPrice();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getExtendedPriceInCents();
        }
    },

    DISCOUNT("l_discount", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.getDiscount();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getDiscountPercent();
        }
    },

    TAX("l_tax", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.getTax();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.getTaxPercent();
        }
    },

    RETURN_FLAG("l_returnflag", varchar(1)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.getReturnFlag();
        }
    },

    STATUS("l_linestatus", varchar(1)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.getStatus();
        }
    },

    SHIP_DATE("l_shipdate", DATE) {
        @Override
        public String getString(LineItem lineItem)
        {
            return formatDate(getDate(lineItem));
        }

        @Override
        public int getDate(LineItem lineItem)
        {
            return lineItem.getShipDate();
        }
    },

    COMMIT_DATE("l_commitdate", DATE) {
        @Override
        public String getString(LineItem lineItem)
        {
            return formatDate(getDate(lineItem));
        }

        @Override
        public int getDate(LineItem lineItem)
        {
            return lineItem.getCommitDate();
        }
    },

    RECEIPT_DATE("l_receiptdate", DATE) {
        @Override
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

    SHIP_INSTRUCTIONS("l_shipinstruct", varchar(25)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.getShipInstructions();
        }
    },

    SHIP_MODE("l_shipmode", varchar(10)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.getShipMode();
        }
    },

    COMMENT("l_comment", varchar(44)) {
        @Override
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
