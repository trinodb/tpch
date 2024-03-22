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

import static io.trino.tpch.TpchColumnTypes.IDENTIFIER;
import static io.trino.tpch.TpchColumnTypes.varchar;

public enum NationColumn
        implements TpchColumn<Nation>
{
    NATION_KEY("n_nationkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Nation nation)
        {
            return nation.nationKey();
        }
    },

    NAME("n_name", varchar(25)) {
        @Override
        public String getString(Nation nation)
        {
            return nation.name();
        }
    },

    REGION_KEY("n_regionkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Nation nation)
        {
            return nation.regionKey();
        }
    },

    COMMENT("n_comment", varchar(152)) {
        @Override
        public String getString(Nation nation)
        {
            return nation.comment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    NationColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Nation entity)
    {
        throw new UnsupportedOperationException();
    }
}
