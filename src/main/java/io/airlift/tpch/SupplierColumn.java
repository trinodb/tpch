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

public enum SupplierColumn
        implements TpchColumn<Supplier>
{
    @SuppressWarnings("SpellCheckingInspection")
    SUPPLIER_KEY("suppkey", Long.class)
            {
                public long getLong(Supplier supplier)
                {
                    return supplier.getSupplierKey();
                }
            },

    NAME("name", String.class)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getName();
                }
            },

    ADDRESS("address", String.class)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getAddress();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    NATION_KEY("nationkey", Long.class)
            {
                public long getLong(Supplier supplier)
                {
                    return supplier.getNationKey();
                }
            },

    PHONE("phone", String.class)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getPhone();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    ACCOUNT_BALANCE("acctbal", Double.class)
            {
                public double getDouble(Supplier supplier)
                {
                    return supplier.getAccountBalance();
                }
            },

    COMMENT("comment", String.class)
            {
                public String getString(Supplier supplier)
                {
                    return supplier.getComment();
                }
            };

    private final String columnName;
    private final Class<?> type;

    SupplierColumn(String columnName, Class<?> type)
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
}
