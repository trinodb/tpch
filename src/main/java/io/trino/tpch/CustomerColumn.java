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

import static io.trino.tpch.TpchColumnTypes.DOUBLE;
import static io.trino.tpch.TpchColumnTypes.IDENTIFIER;
import static io.trino.tpch.TpchColumnTypes.varchar;

public enum CustomerColumn
        implements TpchColumn<Customer>
{
    CUSTOMER_KEY("c_custkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Customer customer)
        {
            return customer.customerKey();
        }
    },

    NAME("c_name", varchar(25)) {
        @Override
        public String getString(Customer customer)
        {
            return customer.name();
        }
    },

    ADDRESS("c_address", varchar(40)) {
        @Override
        public String getString(Customer customer)
        {
            return customer.address();
        }
    },

    NATION_KEY("c_nationkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Customer customer)
        {
            return customer.nationKey();
        }
    },

    PHONE("c_phone", varchar(15)) {
        @Override
        public String getString(Customer customer)
        {
            return customer.phone();
        }
    },

    ACCOUNT_BALANCE("c_acctbal", DOUBLE) {
        @Override
        public double getDouble(Customer customer)
        {
            return customer.accountBalance();
        }

        @Override
        public long getIdentifier(Customer customer)
        {
            return customer.accountBalanceInCents();
        }
    },

    MARKET_SEGMENT("c_mktsegment", varchar(10)) {
        @Override
        public String getString(Customer customer)
        {
            return customer.marketSegment();
        }
    },

    COMMENT("c_comment", varchar(117)) {
        @Override
        public String getString(Customer customer)
        {
            return customer.comment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    CustomerColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Customer customer)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Customer entity)
    {
        throw new UnsupportedOperationException();
    }
}
