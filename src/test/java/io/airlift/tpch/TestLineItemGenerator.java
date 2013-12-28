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

public class TestLineItemGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 60000, "a4475303a4f96151fa6e81bf70623785");
        assertPartialMD5(1, 19999, 60000, "97f16b82020371cb116e4dc0b19b8ab5");
        assertPartialMD5(1, 39998, 60000, "64ad22f4331a98245d620c481f6f3fef");
        assertPartialMD5(1, 59997, 60000, "09cc90b65a046c925bfdcb8eb8f297f2");
        assertPartialMD5(1, 60000, 60000, "10d54dd21be687c538df07d46a25ebc4");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 6000000, "d81c9e53bca2800c94c6e93265bb84b1");
        assertPartialMD5(100, 1999999, 6000000, "c771552fce77cdaa5816d14e35c90444");
        assertPartialMD5(100, 3999998, 6000000, "b3fa1d9fa653646d01d2eefe7e6bbb48");
        assertPartialMD5(100, 5999997, 6000000, "e4e68a2ffea95bf8e65a76cc2674e526");
        assertPartialMD5(100, 6000000, 6000000, "b5a91159f25d26dad7593c0bbe3d89e0");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 18000000, "f45152995cb3b3bd5ec5f44b8d042646");
        assertPartialMD5(300, 5999999, 18000000, "2b74310f1252910eea1e8fa4f619326e");
        assertPartialMD5(300, 11999998, 18000000, "554888bba9a2302331793a0712086570");
        assertPartialMD5(300, 17999997, 18000000, "01151b698c5f53fd7f5e5949bdd9192d");
        assertPartialMD5(300, 18000000, 18000000, "ba375d01c2fdecd256e91bbb65bd02e4");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 60000000, "c1d12e6a14af27249c21c4b60855d4c8");
        assertPartialMD5(1000, 19999999, 60000000, "e6346fbd03a64da9c1557305beacfe27");
        assertPartialMD5(1000, 39999998, 60000000, "2eee76c7f909496f34075f4452102b1a");
        assertPartialMD5(1000, 59999997, 60000000, "4c79161c3dc3f361ce528fce0a1022c7");
        assertPartialMD5(1000, 60000000, 60000000, "72afbf1577014e8bb5e01001420ea31a");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 180000000, "a06c5f8385731a225352517cc95327d3");
        assertPartialMD5(3000, 59999999, 180000000, "154252ca06bdec4dc7b2e83a2c66960e");
        assertPartialMD5(3000, 119999998, 180000000, "17a759f2a53e28db00f9467ec643454d");
        assertPartialMD5(3000, 179999997, 180000000, "615169818e2556f1b56bd05baf9cfc29");
        assertPartialMD5(3000, 180000000, 180000000, "c0be9ee0e5837cd2c4c3563a2563d163");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 600000000, "82d3380381b3a3d165a7ae5e96ab6abc");
        assertPartialMD5(10000, 199999999, 600000000, "e90590009c074637814c614693355ad8");
        assertPartialMD5(10000, 399999998, 600000000, "93c3c140ebdce447c8d4504098cf191a");
        assertPartialMD5(10000, 599999997, 600000000, "b520aa9ef5a933d88938d687cb656d58");
        assertPartialMD5(10000, 600000000, 600000000, "ad008f87943ead5b556e5c828a7ffacf");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 1800000000, "5a542cf41472be416b57fc4d49c8240f");
        assertPartialMD5(30000, 599999999, 1800000000, "b3547b5d34cf775183739a0ea2da0693");
        assertPartialMD5(30000, 1199999998, 1800000000, "c01267249b0a25f49d44800df328e21a");
        assertPartialMD5(30000, 1799999997, 1800000000, "75b1944d17c20d3dec647d2cb71a7b43");
        assertPartialMD5(30000, 1800000000, 1800000000, "f56c433aa27c3ff6e63d75660f440d86");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 2000000000, "fee805435f859eca2dc5c9d6532551fd");
        assertPartialMD5(100000, 666666666, 2000000000, "2ae4a28acc6ec875c2be0b35765c0a22");
        assertPartialMD5(100000, 1333333332, 2000000000, "3fbd419ac271b1659759b0856e5f1915");
        assertPartialMD5(100000, 1999999998, 2000000000, "dc7a452ff3ece15f8c7a2fadea5fba6c");
        assertPartialMD5(100000, 2000000000, 2000000000, "a0f20a1cbcd673cce22679b90b9f9941");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new LineItemGenerator(scaleFactor, step, children), expectedMD5);
    }
}
