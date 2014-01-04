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

public enum NationColumn
        implements TpchColumn<Nation>
{
    @SuppressWarnings("SpellCheckingInspection")
    NATION_KEY("nationkey", Long.class)
            {
                public long getLong(Nation nation)
                {
                    return nation.getNationKey();
                }
            },

    NAME("name", String.class)
            {
                public String getString(Nation nation)
                {
                    return nation.getName();
                }
            },

    @SuppressWarnings("SpellCheckingInspection")
    REGION_KEY("regionkey", Long.class)
            {
                public long getLong(Nation nation)
                {
                    return nation.getRegionKey();
                }
            },

    COMMENT("comment", String.class)
            {
                public String getString(Nation nation)
                {
                    return nation.getComment();
                }
            };

    private final String columnName;
    private final Class<?> type;

    NationColumn(String columnName, Class<?> type)
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
    public double getDouble(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(Nation nation)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(Nation nation)
    {
        throw new UnsupportedOperationException();
    }
}
