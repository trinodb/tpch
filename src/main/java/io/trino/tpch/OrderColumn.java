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

public enum OrderColumn
        implements TpchColumn<Order>
{
    ORDER_KEY("o_orderkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Order order)
        {
            return order.orderKey();
        }
    },

    CUSTOMER_KEY("o_custkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Order order)
        {
            return order.customerKey();
        }
    },

    ORDER_STATUS("o_orderstatus", varchar(1)) {
        @Override
        public String getString(Order order)
        {
            return String.valueOf(order.orderStatus());
        }
    },

    TOTAL_PRICE("o_totalprice", DOUBLE) {
        @Override
        public double getDouble(Order order)
        {
            return order.totalPrice();
        }

        @Override
        public long getIdentifier(Order order)
        {
            return order.totalPriceInCents();
        }
    },

    ORDER_DATE("o_orderdate", DATE) {
        @Override
        public String getString(Order order)
        {
            return formatDate(getDate(order));
        }

        @Override
        public int getDate(Order order)
        {
            return order.orderDate();
        }
    },

    ORDER_PRIORITY("o_orderpriority", varchar(15)) {
        @Override
        public String getString(Order order)
        {
            return order.orderPriority();
        }
    },

    CLERK("o_clerk", varchar(15)) {
        @Override
        public String getString(Order order)
        {
            return order.clerk();
        }
    },

    SHIP_PRIORITY("o_shippriority", INTEGER) {
        @Override
        public int getInteger(Order order)
        {
            return order.shipPriority();
        }
    },

    COMMENT("o_comment", varchar(79)) {
        @Override
        public String getString(Order order)
        {
            return order.comment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    OrderColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Order order)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Order order)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Order order)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Order order)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Order entity)
    {
        throw new UnsupportedOperationException();
    }
}
