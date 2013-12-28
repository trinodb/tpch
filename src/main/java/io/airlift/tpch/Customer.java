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

public class Customer
        implements TpchEntity
{
    private final long customerKey;
    private final String name;
    private final String address;
    private final long nationKey;
    private final String phone;
    private final long accountBalance;
    private final String marketSegment;
    private final String comment;

    public Customer(long customerKey, String name, String address, long nationKey, String phone, long accountBalance, String marketSegment, String comment)
    {
        this.customerKey = customerKey;
        this.name = name;
        this.address = address;
        this.nationKey = nationKey;
        this.phone = phone;
        this.accountBalance = accountBalance;
        this.marketSegment = marketSegment;
        this.comment = comment;
    }

    public long getCustomerKey()
    {
        return customerKey;
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

    public String getMarketSegment()
    {
        return marketSegment;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return String.format("%d|%s|%s|%d|%s|%s|%s|%s|",
                customerKey,
                name,
                address,
                nationKey,
                phone,
                formatMoney(accountBalance),
                marketSegment,
                comment);
    }
}
