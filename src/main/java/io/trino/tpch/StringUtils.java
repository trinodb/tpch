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

import com.google.common.base.Joiner;

import static com.google.common.base.Strings.padStart;

public class StringUtils
{
    private static final Character COLUMN_SEPARATOR = '|';
    private static final Joiner LINE_JOINER = Joiner.on(COLUMN_SEPARATOR);

    private StringUtils() {}

    public static String padWithZeros(long value, int length)
    {
        return padStart(Long.toString(value), length, '0');
    }

    public static String buildLine(Object... values)
    {
        return LINE_JOINER.join(values) + COLUMN_SEPARATOR;
    }
}
