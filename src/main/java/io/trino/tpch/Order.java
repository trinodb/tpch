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
import static io.trino.tpch.GenerateUtils.formatMoney;
import static io.trino.tpch.StringUtils.buildLine;
import static java.util.Objects.requireNonNull;

public record Order(
        long rowNumber,
        long orderKey,
        long customerKey,
        char orderStatus,
        long totalPriceInCents,
        int orderDate,
        String orderPriority,
        String clerk,
        int shipPriority,
        String comment)
        implements TpchEntity
{
    public Order
    {
        requireNonNull(orderPriority, "orderPriority is null");
        requireNonNull(clerk, "clerk is null");
        requireNonNull(comment, "comment is null");
    }

    public double totalPrice()
    {
        return totalPriceInCents / 100.0;
    }

    @Override
    public String toLine()
    {
        return buildLine(
                orderKey,
                customerKey,
                orderStatus,
                formatMoney(totalPriceInCents),
                formatDate(orderDate),
                orderPriority,
                clerk,
                shipPriority,
                comment);
    }
}
