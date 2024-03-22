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

import static io.trino.tpch.GenerateUtils.formatMoney;
import static io.trino.tpch.StringUtils.buildLine;
import static java.util.Objects.requireNonNull;

public record Supplier(
        long rowNumber,
        long supplierKey,
        String name,
        String address,
        long nationKey,
        String phone,
        long accountBalanceInCents,
        String comment)
        implements TpchEntity
{
    public Supplier
    {
        requireNonNull(name, "name is null");
        requireNonNull(address, "address is null");
        requireNonNull(phone, "phone is null");
        requireNonNull(comment, "comment is null");
    }

    public double getAccountBalance()
    {
        return accountBalanceInCents / 100.0;
    }

    @Override
    public String toLine()
    {
        return buildLine(
                supplierKey,
                name,
                address,
                nationKey,
                phone,
                formatMoney(accountBalanceInCents),
                comment);
    }
}
