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

public enum OrderColumn
        implements TpchColumn<Order>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("orderkey", BIGINT)
            {
                public long getLong(Order order)
                {
                    return order.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    CUSTOMER_KEY("custkey", BIGINT)
            {
                public long getLong(Order order)
                {
                    return order.getCustomerKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_STATUS("orderstatus", VARCHAR)
            {
                public String getString(Order order)
                {
                    return String.valueOf(order.getOrderStatus());
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    TOTAL_PRICE("totalprice", DOUBLE)
            {
                public double getDouble(Order order)
                {
                    return order.getTotalPrice();
                }

                public long getLong(Order order)
                {
                    return order.getTotalPriceInCents();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_DATE("orderdate", DATE)
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
    ORDER_PRIORITY("orderpriority", VARCHAR)
            {
                public String getString(Order order)
                {
                    return order.getOrderPriority();
                }
            },

    CLERK("clerk", VARCHAR)
            {
                public String getString(Order order)
                {
                    return order.getClerk();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_PRIORITY("shippriority", BIGINT)
            {
                public long getLong(Order order)
                {
                    return order.getShipPriority();
                }
            },

    COMMENT("comment", VARCHAR)
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
    public long getLong(Order order)
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
