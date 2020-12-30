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

import static java.util.Locale.ENGLISH;
import static java.util.Objects.requireNonNull;

public class Region
        implements TpchEntity
{
    private final long rowNumber;
    private final long regionKey;
    private final String name;
    private final String comment;

    public Region(long rowNumber, long regionKey, String name, String comment)
    {
        this.rowNumber = rowNumber;
        this.regionKey = regionKey;
        this.name = requireNonNull(name, "name is null");
        this.comment = requireNonNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
    }

    public long getRegionKey()
    {
        return regionKey;
    }

    public String getName()
    {
        return name;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return String.format(ENGLISH, "%d|%s|%s|", regionKey, name, comment);
    }
}
