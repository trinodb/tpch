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

public class TestCustomerGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 1500, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(1, 499, 1500, "808548ba5bd7fbd7cc3f1223b0d24301");
        assertPartialMD5(1, 998, 1500, "4b9debf0a7933b13d496a9f6ab4f59cf");
        assertPartialMD5(1, 1497, 1500, "8ded00759f5ffb82cecc3fd27b50764b");
        assertPartialMD5(1, 1500, 1500, "4aabb2b18a3ba365f925af41a83b966a");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 150000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(100, 49999, 150000, "29680886ae23a18fc62a14ac5e388317");
        assertPartialMD5(100, 99998, 150000, "b128fdd5fe098fcc5fccb8b29f7e261e");
        assertPartialMD5(100, 149997, 150000, "13fba16cc4a60f630b528b391fefd183");
        assertPartialMD5(100, 150000, 150000, "a4f86d4bdfccb728173e033ae5a94c44");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 450000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(300, 149999, 450000, "f0c909f3da0298666ac86dbbfcd9d1d4");
        assertPartialMD5(300, 299998, 450000, "d2a26b6b374f1184c66c84a9a30f9094");
        assertPartialMD5(300, 449997, 450000, "57fcfcc2223b14cf72a5e33399a87b01");
        assertPartialMD5(300, 450000, 450000, "372472ce64fabe8bb48655c345868d75");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 1500000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(1000, 499999, 1500000, "147baed2619ebd3ea7b6af827eee4310");
        assertPartialMD5(1000, 999998, 1500000, "0df7a9b8e2e987ca383af87c907292cc");
        assertPartialMD5(1000, 1499997, 1500000, "5223171ceda654330b35e46f3ce6eefd");
        assertPartialMD5(1000, 1500000, 1500000, "8c850c07d853a6285cb95769628c8593");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 4500000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(3000, 1499999, 4500000, "c69cd893bf2dce65dda6ea358c94ce0b");
        assertPartialMD5(3000, 2999998, 4500000, "300ac282b289ff75923cd0ba9cc8c7c5");
        assertPartialMD5(3000, 4499997, 4500000, "60598d532108318d9f5bb2a172af9855");
        assertPartialMD5(3000, 4500000, 4500000, "19aba55dec85e23b0ed43890991394e5");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 15000000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(10000, 4999999, 15000000, "d0d580d5b869a53037f22d36ca218400");
        assertPartialMD5(10000, 9999998, 15000000, "4def8088287021fc08a2e3d79c95fa6d");
        assertPartialMD5(10000, 14999997, 15000000, "15033a01bd00d4fb7dbc42009eb4de84");
        assertPartialMD5(10000, 15000000, 15000000, "0142f92ce02538ecbb193059745388dc");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 45000000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(30000, 14999999, 45000000, "4c3576608ccfc9f4c2e4d68b4f4b0a46");
        assertPartialMD5(30000, 29999998, 45000000, "94944bfa1008bc759c8b6d90aef6b7fd");
        assertPartialMD5(30000, 44999997, 45000000, "43ca5baffc4f2a0b4df211b8c46a833e");
        assertPartialMD5(30000, 45000000, 45000000, "ae284e0a7df14dda7200da0658304a76");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 150000000, "899926b535e47a5c3ae6096bfe29d210");
        assertPartialMD5(100000, 49999999, 150000000, "78eed97998699458513ebaebd6538667");
        assertPartialMD5(100000, 99999998, 150000000, "6b9687f6fb3ddf0049ba6456964a40a9");
        assertPartialMD5(100000, 149999997, 150000000, "3828e3b1f0ade1022e97eafbf853984c");
        assertPartialMD5(100000, 150000000, 150000000, "1b0226ca8931d8e75b123cfccd36d5c6");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new CustomerGenerator(scaleFactor, step, children), expectedMD5);
    }
}
