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

import static io.airlift.tpch.GenerateUtils.formatMoney;

public class Order
        implements TpchEntity
{
    private final long orderKey;
    private final long customerKey;
    private final char orderStatus;
    private final long totalPrice;
    private final String orderDate;
    private final String orderPriority;
    private final String clerk;
    private final long shipPriority;
    private final String comment;

    public Order(long orderKey,
            long customerKey,
            char orderStatus,
            long totalPrice,
            String orderDate,
            String orderPriority,
            String clerk,
            long shipPriority,
            String comment)
    {
        this.orderKey = orderKey;
        this.customerKey = customerKey;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderPriority = orderPriority;
        this.clerk = clerk;
        this.shipPriority = shipPriority;
        this.comment = comment;
    }

    public long getOrderKey()
    {
        return orderKey;
    }

    public long getCustomerKey()
    {
        return customerKey;
    }

    public char getOrderStatus()
    {
        return orderStatus;
    }

    public double getTotalPrice()
    {
        return totalPrice / 100.0;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public String getOrderPriority()
    {
        return orderPriority;
    }

    public String getClerk()
    {
        return clerk;
    }

    public long getShipPriority()
    {
        return shipPriority;
    }

    public String getComment()
    {
        return comment;
    }

    public String toLine()
    {
        return String.format("%d|%d|%s|%s|%s|%s|%s|%d|%s|",
                orderKey,
                customerKey,
                orderStatus,
                formatMoney(totalPrice),
                orderDate,
                orderPriority,
                clerk,
                shipPriority,
                comment);
    }
}
