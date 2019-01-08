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

import java.util.Objects;
import java.util.Optional;

public class TpchColumnType
{
    public enum Base
    {
        INTEGER,
        IDENTIFIER,
        DATE,
        DOUBLE,
        VARCHAR
    }

    private final Base base;
    private final Optional<Long> precision;
    private final Optional<Long> scale;

    private TpchColumnType(Base base, Optional<Long> precision, Optional<Long> scale)
    {
        this.base = base;
        this.precision = precision;
        this.scale = scale;
    }

    TpchColumnType(Base base, long precision, long scale)
    {
        this(base, Optional.of(precision), Optional.of(scale));
    }

    TpchColumnType(Base base, long precision)
    {
        this(base, Optional.of(precision), Optional.<Long>empty());
    }

    TpchColumnType(Base base)
    {
        this(base, Optional.<Long>empty(), Optional.<Long>empty());
    }

    public Base getBase()
    {
        return base;
    }

    public Optional<Long> getPrecision()
    {
        return precision;
    }

    public Optional<Long> getScale()
    {
        return scale;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TpchColumnType that = (TpchColumnType) o;
        return Objects.equals(base, that.base) &&
                Objects.equals(precision, that.precision) &&
                Objects.equals(scale, that.scale);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(base, precision, scale);
    }
}
