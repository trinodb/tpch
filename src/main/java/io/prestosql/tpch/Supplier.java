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
package io.prestosql.tpch;

import static io.prestosql.tpch.GenerateUtils.formatMoney;
import static java.util.Locale.ENGLISH;
import static java.util.Objects.requireNonNull;

public class Supplier
        extends TpchEntity
{
    private final long rowNumber;
    private final long supplierKey;
    private final String name;
    private final String address;
    private final long nationKey;
    private final String phone;
    private final long accountBalance;
    private final String comment;

    public Supplier(long rowNumber, long supplierKey, String name, String address, long nationKey, String phone, long accountBalance, String comment)
    {
        this.rowNumber = rowNumber;
        this.supplierKey = supplierKey;
        this.name = requireNonNull(name, "name is null");
        this.address = requireNonNull(address, "address is null");
        this.nationKey = nationKey;
        this.phone = requireNonNull(phone, "phone is null");
        this.accountBalance = accountBalance;
        this.comment = requireNonNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
    }

    public long getSupplierKey()
    {
        return supplierKey;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public long getNationKey()
    {
        return nationKey;
    }

    public String getPhone()
    {
        return phone;
    }

    public double getAccountBalance()
    {
        return accountBalance / 100.0;
    }

    public long getAccountBalanceInCents()
    {
        return accountBalance;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return String.format(ENGLISH,
                "%d|%s|%s|%d|%s|%s|%s|",
                supplierKey,
                name,
                address,
                nationKey,
                phone,
                formatMoney(accountBalance),
                comment);
    }
}
