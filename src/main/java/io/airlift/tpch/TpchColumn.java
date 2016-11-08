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

public interface TpchColumn<E extends TpchEntity>
{
    String getColumnName();

    TpchColumnType getType();

    double getDouble(E entity);

    long getIdentifier(E entity);

    int getInteger(E entity);

    String getString(E entity);

    int getDate(E entity);

    String TPCH_COLUMN_VALID_PREFIX_REGEX = "(?i)^(p|ps|l|o|s|c|n|r)_";

    default String getSimplifiedColumnName()
    {
        return getColumnName().replaceFirst(TPCH_COLUMN_VALID_PREFIX_REGEX, "");
    }
}
