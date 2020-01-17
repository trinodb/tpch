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

public class TpchColumnTypes
{
    private TpchColumnTypes() {}

    public static final TpchColumnType INTEGER = new TpchColumnType(TpchColumnType.Base.INTEGER);
    public static final TpchColumnType IDENTIFIER = new TpchColumnType(TpchColumnType.Base.IDENTIFIER);
    public static final TpchColumnType DATE = new TpchColumnType(TpchColumnType.Base.DATE);
    public static final TpchColumnType DOUBLE = new TpchColumnType(TpchColumnType.Base.DOUBLE);

    public static TpchColumnType varchar(long precision)
    {
        return new TpchColumnType(TpchColumnType.Base.VARCHAR, precision);
    }
}
