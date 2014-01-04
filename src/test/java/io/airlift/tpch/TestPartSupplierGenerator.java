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

public class TestPartSupplierGenerator
{
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1()
    {
        assertPartialMD5(1, 1, 8000, "b18d9663a9f6316c73040c131299be01");
        assertPartialMD5(1, 2666, 8000, "3d7ba681929e52460244ecdb6e806eb2");
        assertPartialMD5(1, 5332, 8000, "5b74231816342343d1a9b3e61d49539a");
        assertPartialMD5(1, 7998, 8000, "262f27d1c7a0f04328b0d0d7966f3874");
        assertPartialMD5(1, 8000, 8000, "56924f6f33405c2b2ef1171a08786429");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100()
    {
        assertPartialMD5(100, 1, 800000, "46fb6ba1035ee4042780419b0d39a540");
        assertPartialMD5(100, 266666, 800000, "dc44d8748e06fbcffe4f6218118dd9ef");
        assertPartialMD5(100, 533332, 800000, "7e91c159eb8f3eed31291b186e2cbaf5");
        assertPartialMD5(100, 799998, 800000, "e2bafecf25783fde1ee6767ce10f3b6f");
        assertPartialMD5(100, 800000, 800000, "056ef5366dc1109ddd6c40832c17c5b7");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor300()
    {
        assertPartialMD5(300, 1, 2400000, "4a09468286a8f75935abd6bd61057bc0");
        assertPartialMD5(300, 799999, 2400000, "e19e1bece1ea4ebf482e2d282b56dadc");
        assertPartialMD5(300, 1599998, 2400000, "dfcad441465814de09a72456288177c9");
        assertPartialMD5(300, 2399997, 2400000, "3f128e0bdf3c2d5e264dd4c969dc9b4c");
        assertPartialMD5(300, 2400000, 2400000, "271eb55b53d04c755eba24f1ef97a57f");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor1000()
    {
        assertPartialMD5(1000, 1, 8000000, "8df5b20c899f2ffe0230389a73d15034");
        assertPartialMD5(1000, 2666666, 8000000, "560ced70709e770bf4d414e7d2a1bb89");
        assertPartialMD5(1000, 5333332, 8000000, "c5819efd3964164127568a74f77ef4c4");
        assertPartialMD5(1000, 7999998, 8000000, "6d76a71e02e941b7b468c4f925a56518");
        assertPartialMD5(1000, 8000000, 8000000, "3df40dedfcb7392cdd4423b5311e6c83");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor3000()
    {
        assertPartialMD5(3000, 1, 24000000, "07b3f2f11334c3baa3a5929119fda8cc");
        assertPartialMD5(3000, 7999999, 24000000, "582d954d2cdac4ac3d4d8b1728d5fc99");
        assertPartialMD5(3000, 15999998, 24000000, "1d347930535104c186a5f10c24020a2e");
        assertPartialMD5(3000, 23999997, 24000000, "fc91254d2a9fd94568ee6cd05ddf82be");
        assertPartialMD5(3000, 24000000, 24000000, "8af0ad07b267891b4197ae9e6276e244");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor10000()
    {
        assertPartialMD5(10000, 1, 80000000, "1baeddd9cdc92958ca08ff2f29b9d610");
        assertPartialMD5(10000, 26666666, 80000000, "6db9298059599d34fb56624d2de35ab5");
        assertPartialMD5(10000, 53333332, 80000000, "ac7c429608141367b8570afb3a8b821b");
        assertPartialMD5(10000, 79999998, 80000000, "4a45c21b470934327519da13fd2a049b");
        assertPartialMD5(10000, 80000000, 80000000, "35be88a03dc98a78e56ea10152a0827b");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor30000()
    {
        assertPartialMD5(30000, 1, 240000000, "27023e61766a66f629d37da4ee8878d6");
        assertPartialMD5(30000, 79999999, 240000000, "f1e4979acb9420856deb7cb92a43d8de");
        assertPartialMD5(30000, 159999998, 240000000, "c173e25e4330573f26da551e0499a849");
        assertPartialMD5(30000, 239999997, 240000000, "a9ace264a7284a50baa0e3b9bb8e31ae");
        assertPartialMD5(30000, 240000000, 240000000, "7215747c2f50e71abf33b532bdbf812a");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testScaleFactor100000()
    {
        assertPartialMD5(100000, 1, 800000000, "c3ef6b8c449b3ef1dbf29c396a52b5fa");
        assertPartialMD5(100000, 266666666, 800000000, "a2a280ea892bcbaa093d2d62bd5ee019");
        assertPartialMD5(100000, 533333332, 800000000, "1a9a422dc091ae71f6fa8c2d98319e26");
        assertPartialMD5(100000, 799999998, 800000000, "eb5fabfa010e3490b2eb14e8f9d1f687");
        assertPartialMD5(100000, 800000000, 800000000, "5650aab8e50e803743488261b4062331");
    }

    public static void assertPartialMD5(int scaleFactor, int step, int children, String expectedMD5)
    {
        assertEntityLinesMD5(new PartSupplierGenerator(scaleFactor, step, children), expectedMD5);
    }
}
