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

public class TestPartGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 2000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(1, 666, 2000, "b8ac268c84833599e0ec8a3e6e2a4d0c");
        assertPartialMD5(1, 1332, 2000, "1a25ae6fe6751353838a9adbe1133208");
        assertPartialMD5(1, 1998, 2000, "d9e11058e6e4078d109d03a60a4e0848");
        assertPartialMD5(1, 2000, 2000, "c1368b797ba0db13e354943b27c6fd6b");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 200000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(100, 66666, 200000, "3288fd6fac3b013ada2f97ddaf79a519");
        assertPartialMD5(100, 133332, 200000, "65f24157d469d212d0f5257ab5ba13ca");
        assertPartialMD5(100, 199998, 200000, "d5ddcce3609ea3fc6695a0ed6414b057");
        assertPartialMD5(100, 200000, 200000, "928931c9d8a82255efe770ce00a51317");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 600000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(300, 199999, 600000, "0b992e8e129c874a9ac641969a85bc16");
        assertPartialMD5(300, 399998, 600000, "79238d5ac17dd79e0cf82092189baf06");
        assertPartialMD5(300, 599997, 600000, "12df3d049ed1ea6562150c8dd09307b8");
        assertPartialMD5(300, 600000, 600000, "acef0064fcb2d336d49c9da47ec41ea9");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 2000000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(1000, 666666, 2000000, "9f72ee9cbe6b511b4c6257e02608330e");
        assertPartialMD5(1000, 1333332, 2000000, "1b22868e076d192e79e0f8f97bd63ac4");
        assertPartialMD5(1000, 1999998, 2000000, "56d43a55cca636a9bc4832d13e9426bf");
        assertPartialMD5(1000, 2000000, 2000000, "f2012c7682bef2525ce3f37823fe5411");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 6000000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(3000, 1999999, 6000000, "b193b780c9b1fcbe21102e17597268a2");
        assertPartialMD5(3000, 3999998, 6000000, "00c58c84b0fb6068cc6d17be7db20581");
        assertPartialMD5(3000, 5999997, 6000000, "2bbbd9c55288e2c0be770ab4aeec6c8c");
        assertPartialMD5(3000, 6000000, 6000000, "0ea5daeab0916a57e7e4eb4c75539ddb");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 20000000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(10000, 6666666, 20000000, "122ca97f1d3f1ac4e57668990b4b21da");
        assertPartialMD5(10000, 13333332, 20000000, "8371e698b159f04da17d49f6f0699fbf");
        assertPartialMD5(10000, 19999998, 20000000, "bfdafafbc242bac7d1bcec617fa7e702");
        assertPartialMD5(10000, 20000000, 20000000, "7fedeb865965a9cbc4ae0fb9111ed719");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 60000000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(30000, 19999999, 60000000, "202ac3e8c15c37fdb0e9443c1c90edff");
        assertPartialMD5(30000, 39999998, 60000000, "d3795b3b835fcd774ea12a9154570df2");
        assertPartialMD5(30000, 59999997, 60000000, "37af87480aabd04e48a1d66cde624331");
        assertPartialMD5(30000, 60000000, 60000000, "94f6ed664bcf9aa7a56f46495620ee18");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 200000000, "27b15018efa5a4df3c634c2479001474");
        assertPartialMD5(100000, 66666666, 200000000, "172875f9d6b93be1524249785fb4e1e6");
        assertPartialMD5(100000, 133333332, 200000000, "6c033311d9015717a6e87d87be48ffa0");
        assertPartialMD5(100000, 199999998, 200000000, "980d3768a92e92ce9e33b17a3e3407b5");
        assertPartialMD5(100000, 200000000, 200000000, "980bac8771dba3a247c3e615d0f9dbfa");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new PartGenerator(scaleFactor, step, children), expectedMD5);
    }
}
