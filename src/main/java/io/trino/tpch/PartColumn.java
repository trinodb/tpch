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

public enum PartColumn
        implements TpchColumn<Part>
{
    PART_KEY("p_partkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Part part)
        {
            return part.partKey();
        }
    },

    NAME("p_name", varchar(55)) {
        @Override
        public String getString(Part part)
        {
            return part.name();
        }
    },

    MANUFACTURER("p_mfgr", varchar(25)) {
        @Override
        public String getString(Part part)
        {
            return part.manufacturer();
        }
    },

    BRAND("p_brand", varchar(10)) {
        @Override
        public String getString(Part part)
        {
            return part.brand();
        }
    },

    TYPE("p_type", varchar(25)) {
        @Override
        public String getString(Part part)
        {
            return part.type();
        }
    },

    SIZE("p_size", INTEGER) {
        @Override
        public int getInteger(Part part)
        {
            return part.size();
        }
    },

    CONTAINER("p_container", varchar(10)) {
        @Override
        public String getString(Part part)
        {
            return part.container();
        }
    },

    RETAIL_PRICE("p_retailprice", DOUBLE) {
        @Override
        public double getDouble(Part part)
        {
            return part.retailPrice();
        }

        @Override
        public long getIdentifier(Part part)
        {
            return part.retailPriceInCents();
        }
    },

    COMMENT("p_comment", varchar(23)) {
        @Override
        public String getString(Part part)
        {
            return part.comment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    PartColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Part part)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Part entity)
    {
        throw new UnsupportedOperationException();
    }
}
