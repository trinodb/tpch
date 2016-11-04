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

public enum OrderColumn
        implements TpchColumn<Order>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("o_orderkey", IDENTIFIER)
            {
                public long getIdentifier(Order order)
                {
                    return order.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    CUSTOMER_KEY("o_custkey", IDENTIFIER)
            {
                public long getIdentifier(Order order)
                {
                    return order.getCustomerKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_STATUS("o_orderstatus", varchar(1))
            {
                public String getString(Order order)
                {
                    return String.valueOf(order.getOrderStatus());
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    TOTAL_PRICE("o_totalprice", DOUBLE)
            {
                public double getDouble(Order order)
                {
                    return order.getTotalPrice();
                }

                public long getIdentifier(Order order)
                {
                    return order.getTotalPriceInCents();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_DATE("o_orderdate", DATE)
            {
                @Override
                public String getString(Order order)
                {
                    return formatDate(getDate(order));
                }

                public int getDate(Order order)
                {
                    return order.getOrderDate();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_PRIORITY("o_orderpriority", varchar(15))
            {
                public String getString(Order order)
                {
                    return order.getOrderPriority();
                }
            },

    CLERK("o_clerk", varchar(15))
            {
                public String getString(Order order)
                {
                    return order.getClerk();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_PRIORITY("o_shippriority", INTEGER)
            {
                public int getInteger(Order order)
                {
                    return order.getShipPriority();
                }
            },

    COMMENT("o_comment", varchar(79))
            {
                public String getString(Order order)
                {
                    return order.getComment();
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
