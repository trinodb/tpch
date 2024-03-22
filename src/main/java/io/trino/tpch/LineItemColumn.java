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
import static io.trino.tpch.TpchColumnTypes.DATE;
import static io.trino.tpch.TpchColumnTypes.DOUBLE;
import static io.trino.tpch.TpchColumnTypes.IDENTIFIER;
import static io.trino.tpch.TpchColumnTypes.INTEGER;
import static io.trino.tpch.TpchColumnTypes.varchar;

public enum LineItemColumn
        implements TpchColumn<LineItem>
{
    ORDER_KEY("l_orderkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.orderKey();
        }
    },

    PART_KEY("l_partkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.partKey();
        }
    },

    SUPPLIER_KEY("l_suppkey", IDENTIFIER) {
        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.supplierKey();
        }
    },

    LINE_NUMBER("l_linenumber", INTEGER) {
        @Override
        public int getInteger(LineItem lineItem)
        {
            return lineItem.lineNumber();
        }
    },

    QUANTITY("l_quantity", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.quantity();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.quantity() * 100;
        }
    },

    EXTENDED_PRICE("l_extendedprice", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.extendedPrice();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.extendedPriceInCents();
        }
    },

    DISCOUNT("l_discount", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.discount();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.discountPercent();
        }
    },

    TAX("l_tax", DOUBLE) {
        @Override
        public double getDouble(LineItem lineItem)
        {
            return lineItem.tax();
        }

        @Override
        public long getIdentifier(LineItem lineItem)
        {
            return lineItem.taxPercent();
        }
    },

    RETURN_FLAG("l_returnflag", varchar(1)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.returnFlag();
        }
    },

    STATUS("l_linestatus", varchar(1)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.status();
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
            return lineItem.shipDate();
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
            return lineItem.commitDate();
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
            return lineItem.receiptDate();
        }
    },

    SHIP_INSTRUCTIONS("l_shipinstruct", varchar(25)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.shipInstructions();
        }
    },

    SHIP_MODE("l_shipmode", varchar(10)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.shipMode();
        }
    },

    COMMENT("l_comment", varchar(44)) {
        @Override
        public String getString(LineItem lineItem)
        {
            return lineItem.comment();
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
