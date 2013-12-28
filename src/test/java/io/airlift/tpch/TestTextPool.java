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

public class TestTextPool
{
//    private static final int TEXT_POOL_SIZE = 300 * 1024 * 1024;
//
//    @Test
//    public void test()
//            throws Exception
//    {
//        Distributions distributions = getDefaultDistributions();
//
//        TextPoolGenerator poolGenerator = new TextPoolGenerator(TEXT_POOL_SIZE, distributions);
//
//        String textPool = poolGenerator.generate();
//        File file = new File("text-pool.txt");
//        Files.write(textPool, file, Charsets.UTF_8);
//
//        System.out.println("text file : " + file.length());
//
//        File zipFile = new File("text-pool.txt.gz");
//        try (Writer out = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(zipFile)))) {
//            out.write(textPool);
//        }
//
//        System.out.println("zip file : " + zipFile.length());
//
//
////        for (int i = 0; i < 100; i++) {
////            long start = System.nanoTime();
////            String textPool = poolGenerator.generate();
////            System.out.println(Duration.nanosSince(start).convertToMostSuccinctTimeUnit() + "   " + textPool.hashCode());
////
////        }
////
////        Thread.sleep(1000);
//    }
//
//    @Test
//    public void testLoad()
//            throws Exception
//    {
//        File zipFile = new File("text-pool.txt");
//
//        byte[] text = new byte[TEXT_POOL_SIZE];
//        FileInputStream in = new FileInputStream(zipFile);
//        in.read(text);
//
//
////        try (Reader in = new InputStreamReader(new GZIPInputStream(new FileInputStream(zipFile)))) {
//        try (Reader in = new InputStreamReader(in)) {
//            long start = System.nanoTime();
//
//            String textPool = CharStreams.toString(in);
//            System.out.println(Duration.nanosSince(start).convertToMostSuccinctTimeUnit() + "   " + textPool.hashCode());
//        }
//    }
}


// text file : 314_572_800
//  zip file :  51_918_439
