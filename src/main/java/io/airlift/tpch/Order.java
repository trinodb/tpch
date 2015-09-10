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

import static com.google.common.base.Preconditions.checkNotNull;
import static io.airlift.tpch.GenerateUtils.formatDate;
import static io.airlift.tpch.GenerateUtils.formatMoney;
import static java.util.Locale.ENGLISH;

public class Order
        implements TpchEntity
{
    private final long rowNumber;
    private final long orderKey;
    private final long customerKey;
    private final char orderStatus;
    private final long totalPrice;
    private final int orderDate;
    private final String orderPriority;
    private final String clerk;
    private final long shipPriority;
    private final String comment;

    public Order(long rowNumber,
            long orderKey,
            long customerKey,
            char orderStatus,
            long totalPrice,
            int orderDate,
            String orderPriority,
            String clerk,
            long shipPriority,
            String comment)
    {
        this.rowNumber = rowNumber;
        this.orderKey = orderKey;
        this.customerKey = customerKey;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderPriority = checkNotNull(orderPriority, "orderPriority is null");
        this.clerk = checkNotNull(clerk, "clerk is null");
        this.shipPriority = shipPriority;
        this.comment = checkNotNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
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

    public long getTotalPriceInCents()
    {
        return totalPrice;
    }

    public int getOrderDate()
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

    @Override
    public String toLine()
    {
        return String.format(ENGLISH,
                "%d|%d|%s|%s|%s|%s|%s|%d|%s|",
                orderKey,
                customerKey,
                orderStatus,
                formatMoney(totalPrice),
                formatDate(orderDate),
                orderPriority,
                clerk,
                shipPriority,
                comment);
    }
}
