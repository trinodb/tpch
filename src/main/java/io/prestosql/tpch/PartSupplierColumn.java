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

import static io.prestosql.tpch.TpchColumnTypes.DOUBLE;
import static io.prestosql.tpch.TpchColumnTypes.IDENTIFIER;
import static io.prestosql.tpch.TpchColumnTypes.INTEGER;
import static io.prestosql.tpch.TpchColumnTypes.varchar;

public enum PartSupplierColumn
        implements TpchColumn<PartSupplier>
{
    PART_KEY("ps_partkey", IDENTIFIER) {
        @Override
        public long getIdentifier(PartSupplier partSupplier)
        {
            return partSupplier.getPartKey();
        }
    },

    SUPPLIER_KEY("ps_suppkey", IDENTIFIER) {
        @Override
        public long getIdentifier(PartSupplier partSupplier)
        {
            return partSupplier.getSupplierKey();
        }
    },

    AVAILABLE_QUANTITY("ps_availqty", INTEGER) {
        @Override
        public int getInteger(PartSupplier partSupplier)
        {
            return partSupplier.getAvailableQuantity();
        }
    },

    SUPPLY_COST("ps_supplycost", DOUBLE) {
        @Override
        public double getDouble(PartSupplier partSupplier)
        {
            return partSupplier.getSupplyCost();
        }

        @Override
        public long getIdentifier(PartSupplier partSupplier)
        {
            return partSupplier.getSupplyCostInCents();
        }
    },

    COMMENT("ps_comment", varchar(199)) {
        @Override
        public String getString(PartSupplier partSupplier)
        {
            return partSupplier.getComment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    PartSupplierColumn(String columnName, TpchColumnType type)
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
    public double getDouble(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(PartSupplier partSupplier)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(PartSupplier entity)
    {
        throw new UnsupportedOperationException();
    }
}
