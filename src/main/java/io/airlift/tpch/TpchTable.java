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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public abstract class TpchTable<E extends TpchEntity>
{
    public static final TpchTable<Customer> CUSTOMER = new TpchTable<Customer>("customer", CustomerColumn.values())
    {
        @Override
        public Iterable<Customer> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new CustomerGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<Order> ORDERS = new TpchTable<Order>("orders", OrderColumn.values())
    {
        @Override
        public Iterable<Order> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new OrderGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<LineItem> LINE_ITEM = new TpchTable<LineItem>("lineitem", LineItemColumn.values())
    {
        @Override
        public Iterable<LineItem> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new LineItemGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<Part> PART = new TpchTable<Part>("part", PartColumn.values())
    {
        @Override
        public Iterable<Part> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new PartGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<PartSupplier> PART_SUPPLIER = new TpchTable<PartSupplier>("partsupp", PartSupplierColumn.values())
    {
        @Override
        public Iterable<PartSupplier> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new PartSupplierGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<Supplier> SUPPLIER = new TpchTable<Supplier>("supplier", SupplierColumn.values())
    {
        @Override
        public Iterable<Supplier> createGenerator(double scaleFactor, int part, int partCount)
        {
            return new SupplierGenerator(scaleFactor, part, partCount);
        }
    };

    public static final TpchTable<Nation> NATION = new TpchTable<Nation>("nation", NationColumn.values())
    {
        @Override
        public Iterable<Nation> createGenerator(double scaleFactor, int part, int partCount)
        {
            if (part != 1) {
                return ImmutableList.of();
            }
            return new NationGenerator();
        }
    };

    public static final TpchTable<Region> REGION = new TpchTable<Region>("region", RegionColumn.values())
    {
        @Override
        public Iterable<Region> createGenerator(double scaleFactor, int part, int partCount)
        {
            if (part != 1) {
                return ImmutableList.of();
            }
            return new RegionGenerator();
        }
    };

    private static final List<TpchTable<?>> TABLES;
    private static final Map<String, TpchTable<?>> TABLES_BY_NAME;

    static {
        TABLES = ImmutableList.of(CUSTOMER, ORDERS, LINE_ITEM, PART, PART_SUPPLIER, SUPPLIER, NATION, REGION);
        TABLES_BY_NAME = Maps.uniqueIndex(TABLES, TpchTable::getTableName);
    }

    public static List<TpchTable<?>> getTables()
    {
        return TABLES;
    }

    public static TpchTable<?> getTable(String tableName)
    {
        TpchTable<?> table = TABLES_BY_NAME.get(tableName);
        Preconditions.checkArgument(table != null, "Table %s not found", tableName);
        return table;
    }

    private final String tableName;
    private final List<TpchColumn<E>> columns;
    private final Map<String, TpchColumn<E>> columnsByName;

    private TpchTable(String tableName, TpchColumn<E>[] columns)
    {
        this.tableName = tableName;
        this.columns = ImmutableList.copyOf(columns);
        this.columnsByName = new ImmutableMap.Builder<String, TpchColumn<E>>()
                .putAll(Maps.uniqueIndex(this.columns, TpchColumn::getColumnName))
                .putAll(Maps.uniqueIndex(this.columns, TpchColumn::getSimplifiedColumnName))
                .build();
    }

    public String getTableName()
    {
        return tableName;
    }

    public List<TpchColumn<E>> getColumns()
    {
        return columns;
    }

    public TpchColumn<E> getColumn(String columnName)
    {
        TpchColumn<E> column = columnsByName.get(columnName);
        Preconditions.checkArgument(column != null, "Table %s does not have a column %s", tableName, columnName);
        return column;
    }

    public abstract Iterable<E> createGenerator(double scaleFactor, int part, int partCount);
}
