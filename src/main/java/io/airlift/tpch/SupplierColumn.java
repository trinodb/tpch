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

import static io.airlift.tpch.TpchColumnType.BIGINT;
import static io.airlift.tpch.TpchColumnType.DOUBLE;
import static io.airlift.tpch.TpchColumnType.VARCHAR;

public enum SupplierColumn
        implements TpchColumn<Supplier>
{
    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("suppkey", BIGINT)
            {
                public long getLong(Supplier supplier)
                {
                    return supplier.getSupplierKey();
                }
            },

    NAME("name", VARCHAR)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getName();
                }
            },

    ADDRESS("address", VARCHAR)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getAddress();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    NATION_KEY("nationkey", BIGINT)
            {
                public long getLong(Supplier supplier)
                {
                    return supplier.getNationKey();
                }
            },

    PHONE("phone", VARCHAR)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getPhone();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ACCOUNT_BALANCE("acctbal", DOUBLE)
            {
                public double getDouble(Supplier supplier)
                {
                    return supplier.getAccountBalance();
                }

                public long getLong(Supplier supplier)
                {
                    return supplier.getAccountBalanceInCents();
                }
            },

    COMMENT("comment", VARCHAR)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getComment();
                }
            };

    private final String columnName;
    private final TpchColumnType type;

    SupplierColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Supplier supplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(Supplier supplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Supplier supplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Supplier entity)
    {
        throw new UnsupportedOperationException();
    }
}
