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

public class Supplier
        implements TpchEntity
{
    public long supplierKey;
    public String name;
    public String address;
    public long nationKey;
    public String phone;
    public long accountBalance;
    public String comment;

    public Supplier(long supplierKey, String name, String address, long nationKey, String phone, long accountBalance, String comment)
    {
        this.supplierKey = supplierKey;
        this.name = name;
        this.address = address;
        this.nationKey = nationKey;
        this.phone = phone;
        this.accountBalance = accountBalance;
        this.comment = comment;
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

    public String getComment()
    {
        return comment;
    }

    public String toLine()
    {
        return String.format("%d|%s|%s|%d|%s|%s|%s|",
                supplierKey,
                name,
                address,
                nationKey,
                phone,
                formatMoney(accountBalance),
                comment);
    }
}
