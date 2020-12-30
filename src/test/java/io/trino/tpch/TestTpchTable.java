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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestTpchTable
{
    @Test
    public void testGetColumn()
    {
        TpchTable.getTables().forEach(table -> {
            table.getColumns().forEach(column -> {
                assertEquals(table.getColumn(column.getColumnName()), column);
                assertEquals(table.getColumn(column.getSimplifiedColumnName()), column);
            });
        });
    }
}
