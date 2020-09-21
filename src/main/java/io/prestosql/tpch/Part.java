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

import static io.prestosql.tpch.GenerateUtils.formatMoney;
import static java.util.Locale.ENGLISH;
import static java.util.Objects.requireNonNull;

public class Part
        extends TpchEntity
{
    private final long rowNumber;
    private final long partKey;
    private final String name;
    private final String manufacturer;
    private final String brand;
    private final String type;
    private final int size;
    private final String container;
    private final long retailPrice;
    private final String comment;

    public Part(long rowNumber,
            long partKey,
            String name,
            String manufacturer,
            String brand,
            String type,
            int size,
            String container,
            long retailPrice,
            String comment)
    {
        this.rowNumber = rowNumber;
        this.partKey = partKey;
        this.name = requireNonNull(name, "name is null");
        this.manufacturer = requireNonNull(manufacturer, "manufacturer is null");
        this.brand = requireNonNull(brand, "brand is null");
        this.type = requireNonNull(type, "type is null");
        this.size = size;
        this.container = requireNonNull(container, "container is null");
        this.retailPrice = retailPrice;
        this.comment = requireNonNull(comment, "comment is null");
    }

    @Override
    public long getRowNumber()
    {
        return rowNumber;
    }

    public long getPartKey()
    {
        return partKey;
    }

    public String getName()
    {
        return name;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getType()
    {
        return type;
    }

    public int getSize()
    {
        return size;
    }

    public String getContainer()
    {
        return container;
    }

    public double getRetailPrice()
    {
        return retailPrice / 100.0;
    }

    public long getRetailPriceInCents()
    {
        return retailPrice;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public String toLine()
    {
        return String.format(ENGLISH,
                "%d|%s|%s|%s|%s|%d|%s|%s|%s|",
                partKey,
                name,
                manufacturer,
                brand,
                type,
                size,
                container,
                formatMoney(retailPrice),
                comment);
    }
}
