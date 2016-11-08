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

import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestTpchColumn
{
    @Test
    public void testAllColumnNamesHaveValidPrefixes()
    {
        forEachTpchColumn(column -> {
            String columnName = column.getColumnName();
            assertTrue(hasValidColumnPrefix(columnName),
                    format("Invalid column name prefix for column %s (%s)", column, columnName));
        });
    }

    @Test
    public void testForbidsInvalidTpchPrefixes()
    {
        assertFalse(hasValidColumnPrefix("order"));
        assertFalse(hasValidColumnPrefix("z_order"));
    }

    private boolean hasValidColumnPrefix(String columnName)
    {
        String columnNameLowercase = columnName.toLowerCase();
        return Stream.of("p_", "ps_", "l_", "o_", "s_", "c_", "n_", "r_")
                .anyMatch(columnNameLowercase::startsWith);
    }

    @Test
    public void testSimplifiedColumnNamesAreSuffixesOfColumnNames()
    {
        forEachTpchColumn(column -> assertEquals(column.getSimplifiedColumnName(), stripPrefix(column.getColumnName())));
    }

    private String stripPrefix(String columnName)
    {
        return columnName.replaceFirst("\\w{1,2}_", "");
    }

    private void forEachTpchColumn(Consumer<TpchColumn> columnConsumer)
    {
        List<Class<? extends TpchColumn>> columnEnums = asList(
                CustomerColumn.class,
                LineItemColumn.class,
                NationColumn.class,
                OrderColumn.class,
                PartColumn.class,
                PartSupplierColumn.class,
                RegionColumn.class,
                SupplierColumn.class
        );

        columnEnums.forEach((Class<? extends TpchColumn> columnEnumClass) -> {
            asList(columnEnumClass.getEnumConstants()).forEach(columnConsumer::accept);
        });
    }
}
