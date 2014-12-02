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
import static io.airlift.tpch.TpchColumnType.VARCHAR;

public enum RegionColumn
        implements TpchColumn<Region>
{
    @SuppressWarnings("SpellCheckingInspection")
    REGION_KEY("regionkey", BIGINT)
            {
                public long getLong(Region region)
                {
                    return region.getRegionKey();
                }
            },

    NAME("name", VARCHAR)
            {
                public String getString(Region region)
                {
                    return region.getName();
                }
            },

    COMMENT("comment", VARCHAR)
            {
                public String getString(Region region)
                {
                    return region.getComment();
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
    public long getLong(Region region)
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
