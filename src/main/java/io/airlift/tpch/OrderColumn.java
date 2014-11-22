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

public enum OrderColumn
        implements TpchColumn<Order>
{
    @SuppressWarnings("SpellCheckingInspection")
    ORDER_KEY("orderkey", Long.class)
            {
                public long getLong(Order order)
                {
                    return order.getOrderKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    CUSTOMER_KEY("custkey", Long.class)
            {
                public long getLong(Order order)
                {
                    return order.getCustomerKey();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_STATUS("orderstatus", String.class)
            {
                public String getString(Order order)
                {
                    return String.valueOf(order.getOrderStatus());
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    TOTAL_PRICE("totalprice", Double.class)
            {
                public double getDouble(Order order)
                {
                    return order.getTotalPrice();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ORDER_DATE("orderdate", Integer.class)
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
    ORDER_PRIORITY("orderpriority", String.class)
            {
                public String getString(Order order)
                {
                    return order.getOrderPriority();
                }
            },

    CLERK("clerk", String.class)
            {
                public String getString(Order order)
                {
                    return order.getClerk();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    SHIP_PRIORITY("shippriority", Long.class)
            {
                public long getLong(Order order)
                {
                    return order.getShipPriority();
                }
            },

    COMMENT("comment", String.class)
            {
                public String getString(Order order)
                {
                    return order.getComment();
                }
            };

    private final String columnName;
    private final Class<?> type;

    OrderColumn(String columnName, Class<?> type)
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
