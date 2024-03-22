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

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.google.common.io.BaseEncoding.base16;
import static java.io.OutputStream.nullOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.testng.Assert.assertEquals;

public final class GeneratorAssertions
{
    private GeneratorAssertions()
    {
    }

    public static void assertEntityLinesMD5(Iterable<? extends TpchEntity> entities, String expectedMD5)
    {
        try {
            DigestOutputStream out = md5OutputStream(nullOutputStream());
            // out = md5OutputStream(System.out);
            for (TpchEntity entity : entities) {
                out.write(entity.toLine().getBytes(UTF_8));
                out.write('\n');
            }

            byte[] md5Digest = out.getMessageDigest().digest();
            assertEquals(base16().lowerCase().encode(md5Digest), expectedMD5);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static DigestOutputStream md5OutputStream(OutputStream out)
    {
        try {
            return new DigestOutputStream(out, MessageDigest.getInstance("MD5"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
