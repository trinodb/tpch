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

public class TestOrderGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 15000, "1dbe29c547d325eddf9600dfc9625a63");
        assertPartialMD5(1, 4999, 15000, "b8388943dd2d6bf7c4f3241376303d57");
        assertPartialMD5(1, 9998, 15000, "d098736c42c4e29691c8433c367b9663");
        assertPartialMD5(1, 14997, 15000, "2f0878efa67f65709a5f5529751d193e");
        assertPartialMD5(1, 15000, 15000, "30f2e865362c2cd7fcca5f6e56709b5a");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 1500000, "aafe771696b224148e4c4510167ceadf");
        assertPartialMD5(100, 499999, 1500000, "f9a6b20401ffa9282c281c95c95c7db7");
        assertPartialMD5(100, 999998, 1500000, "a425c431021063ddc51c1f4eba8238cc");
        assertPartialMD5(100, 1499997, 1500000, "e0ebfe91a233fb9558073934d7e65404");
        assertPartialMD5(100, 1500000, 1500000, "1b4e8383c39091000f6855d2bd0dd96e");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 4500000, "11a0ca8c6a757f3085410a8e1ad09171");
        assertPartialMD5(300, 1499999, 4500000, "21219466bbd97e352a965d82f96b3d72");
        assertPartialMD5(300, 2999998, 4500000, "e1c4fc283d357df4536df2e316434d22");
        assertPartialMD5(300, 4499997, 4500000, "8f738c7995be351127940ccaba93e459");
        assertPartialMD5(300, 4500000, 4500000, "54ff7e8626b34e78604455fc5ed20e1b");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 15000000, "e13ae8a456cac371b352b4d5972eb4c3");
        assertPartialMD5(1000, 4999999, 15000000, "ca65f1fb9cdb7ef71153551cb4551218");
        assertPartialMD5(1000, 9999998, 15000000, "cc439d003419240bb5a1030ee3062fec");
        assertPartialMD5(1000, 14999997, 15000000, "db87cabd0564be568e4d0244063e7c9b");
        assertPartialMD5(1000, 15000000, 15000000, "01308672abb438db8bc01f5fc2bf3161");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 45000000, "24c77807af68174ee24e09dde80511dc");
        assertPartialMD5(3000, 14999999, 45000000, "d83a9c983bee84f3e23de3bdf6225a5e");
        assertPartialMD5(3000, 29999998, 45000000, "cc4a95b62b1c97b531b8f673a04185fa");
        assertPartialMD5(3000, 44999997, 45000000, "de36b5478aacb9e6540e52b8f2585306");
        assertPartialMD5(3000, 45000000, 45000000, "b3602c0fe3f77e9c8292c23328a9190c");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 150000000, "658a0abc5f3248a28aeb16de7cdf3d2e");
        assertPartialMD5(10000, 49999999, 150000000, "51bd6ccff15bcdbe0274a29ed2bfcfab");
        assertPartialMD5(10000, 99999998, 150000000, "28950b405388e587cfdb0239e9f9444e");
        assertPartialMD5(10000, 149999997, 150000000, "359df0511b5ccbda95b7d3c6f54ceb99");
        assertPartialMD5(10000, 150000000, 150000000, "b505b0d2d09a32b268cc04f6e362213b");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 450000000, "ad1cfc8e31bbacfd3e2723980523769e");
        assertPartialMD5(30000, 149999999, 450000000, "df197c2cd1d4f935555a1387983bfcae");
        assertPartialMD5(30000, 299999998, 450000000, "4fa65762935651a47f396d35f17daf04");
        assertPartialMD5(30000, 449999997, 450000000, "862b8c16b8c6bde264196dc492eff6aa");
        assertPartialMD5(30000, 450000000, 450000000, "40c0bea2004d587ad3e035f0a2ddbb80");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 1500000000, "92008cafae968f3d584ac16a3a6c7485");
        assertPartialMD5(100000, 499999999, 1500000000, "8de8e4261d196baab2c11c83b1fabc7c");
        assertPartialMD5(100000, 999999998, 1500000000, "991488ac520af08655ec1f26376ec7cd");
        assertPartialMD5(100000, 1499999997, 1500000000, "4d9726b8015c87586fbb089b14faaf47");
        assertPartialMD5(100000, 1500000000, 1500000000, "be84c2b78fe41a196c8f30592ccae11e");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new OrderGenerator(scaleFactor, step, children), expectedMD5);
    }
}
