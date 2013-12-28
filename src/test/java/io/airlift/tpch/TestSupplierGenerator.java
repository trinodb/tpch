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

import static io.airlift.tpch.GeneratorAssertions.assertEntityLinesMD5;

public class TestSupplierGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 100, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(1, 32, 100, "b204aa1b70ee6afb0255d3ee3009dbc7");
        assertPartialMD5(1, 64, 100, "2794af79b950dd584343060a8da35c31");
        assertPartialMD5(1, 96, 100, "f723456dc2c6e2787d088896dd5fee3d");
        assertPartialMD5(1, 100, 100, "1c94e08eb316a3a6295b52362b558f26");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 10000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(100, 3332, 10000, "b612bd669b3604f06f30068621a73d6b");
        assertPartialMD5(100, 6664, 10000, "9b546c441fcb23feefff457692c8d316");
        assertPartialMD5(100, 9996, 10000, "552b0582f45fc57c46b185a6e3250729");
        assertPartialMD5(100, 10000, 10000, "6b98232e8bf2d434d2e9d5492ce89145");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 30000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(300, 9999, 30000, "cd1fdaeba822a723983f0d444913bf9b");
        assertPartialMD5(300, 19998, 30000, "59d3033e63605b6c03d147e3be3bb963");
        assertPartialMD5(300, 29997, 30000, "227935ffe6f922dd309c7dc56b170f74");
        assertPartialMD5(300, 30000, 30000, "ec0c30f8250d27b1f9ad1926f73ebbc2");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 100000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(1000, 33332, 100000, "546907f167dc97b2007844564e2b6a5d");
        assertPartialMD5(1000, 66664, 100000, "31ea01f06e82e73edf055775e4f6a863");
        assertPartialMD5(1000, 99996, 100000, "e62c3dc56c6eeaff52444b7d5bd773c9");
        assertPartialMD5(1000, 100000, 100000, "f72f9d134b8b3e82190ab944c8b48baa");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 300000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(3000, 99999, 300000, "fec1659dcdba3e55e0b796a3e239d745");
        assertPartialMD5(3000, 199998, 300000, "18c5bb3361b5b83a8ddbc61f107c6b1a");
        assertPartialMD5(3000, 299997, 300000, "50aee57a320ba4f9e8854e31ec8ffe5e");
        assertPartialMD5(3000, 300000, 300000, "7d50bf61af8ce31f333b5b8e1659b278");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 1000000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(10000, 333332, 1000000, "6c337321ac6293e0243e44c66d882764");
        assertPartialMD5(10000, 666664, 1000000, "6b4359b23457001b90e873d03338c761");
        assertPartialMD5(10000, 999996, 1000000, "8308960a8b1817b8651ecdbe48986005");
        assertPartialMD5(10000, 1000000, 1000000, "0443f89422c079d8789848c98245ff63");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 3000000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(30000, 999999, 3000000, "bd679dfaf6cea22a68f45892694b7d7e");
        assertPartialMD5(30000, 1999998, 3000000, "3944090df3fe13d28a391abeac407a87");
        assertPartialMD5(30000, 2999997, 3000000, "221e5a4df8e7732fdc5ee153cb925516");
        assertPartialMD5(30000, 3000000, 3000000, "233a0472806fc0c2dfd4236d1ce01faa");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 10000000, "56e0621c472064c2a998757c70b44043");
        assertPartialMD5(100000, 3333332, 10000000, "655e7613ded0ace41b8d6231898780a6");
        assertPartialMD5(100000, 6666664, 10000000, "e96dd114dd2850ff9518334656152c79");
        assertPartialMD5(100000, 9999996, 10000000, "90d11d9bc1e7708b1e82dde1d4b93e27");
        assertPartialMD5(100000, 10000000, 10000000, "2c5901932aafd38319b952010919bf1d");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new SupplierGenerator(scaleFactor, step, children), expectedMD5);
    }
}
