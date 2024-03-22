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

public enum RegionColumn
        implements TpchColumn<Region>
{
    REGION_KEY("r_regionkey", IDENTIFIER) {
        @Override
        public long getIdentifier(Region region)
        {
            return region.regionKey();
        }
    },

    NAME("r_name", varchar(25)) {
        @Override
        public String getString(Region region)
        {
            return region.name();
        }
    },

    COMMENT("r_comment", varchar(152)) {
        @Override
        public String getString(Region region)
        {
            return region.comment();
        }
    };

    private final String columnName;
    private final TpchColumnType type;

    RegionColumn(String columnName, TpchColumnType type)
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
    public double getDouble(Region region)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getIdentifier(Region region)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInteger(Region region)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Region region)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDate(Region entity)
    {
        throw new UnsupportedOperationException();
    }
}
