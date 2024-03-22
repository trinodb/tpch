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
import static io.trino.tpch.TpchColumnTypes.INTEGER;
import static io.trino.tpch.TpchColumnTypes.varchar;

public enum PartSupplierColumn
        implements TpchColumn<PartSupplier>
{
    PART_KEY("ps_partkey", IDENTIFIER) {
        @Override
        public long getIdentifier(PartSupplier partSupplier)
        {
            return partSupplier.partKey();
        }
    },

    SUPPLIER_KEY("ps_suppkey", IDENTIFIER) {
        @Override
        public long getIdentifier(PartSupplier partSupplier)
        {
            return partSupplier.supplierKey();
        }
    },

    AVAILABLE_QUANTITY("ps_availqty", INTEGER) {
        @Override
        public int getInteger(PartSupplier partSupplier)
        {
            return partSupplier.availableQuantity();
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
            return partSupplier.supplyCostInCents();
        }
    },

    COMMENT("ps_comment", varchar(199)) {
        @Override
        public String getString(PartSupplier partSupplier)
        {
            return partSupplier.comment();
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
