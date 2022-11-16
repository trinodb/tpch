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

import javax.annotation.concurrent.GuardedBy;

import java.lang.ref.SoftReference;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.SECONDS;

final class SharingTextPoolSupplier
        implements Supplier<TextPool>
{
    private static final Logger log = Logger.getLogger(SharingTextPoolSupplier.class.getName());

    private final ScheduledExecutorService executor;
    private final Supplier<TextPool> loader;

    @GuardedBy("this")
    private SoftReference<TextPool> shared = new SoftReference<>(null);

    public SharingTextPoolSupplier(ScheduledExecutorService executor, Supplier<TextPool> loader)
    {
        this.executor = requireNonNull(executor, "executor is null");
        this.loader = requireNonNull(loader, "loader is null");
    }

    // Synchronize to ensure there is only one TextPool retained at given point in time.
    @Override
    public synchronized TextPool get()
    {
        TextPool textPool = shared.get();
        if (textPool == null) {
            TextPool newTextPool = loader.get();
            log.fine(() -> format("Loaded TextPool %s", Integer.toHexString(System.identityHashCode(newTextPool))));
            shared = new SoftReference<>(newTextPool);
            executor.schedule(
                    () -> {
                        log.fine(() -> format("Releasing strong reference to TextPool %s", Integer.toHexString(System.identityHashCode(newTextPool))));
                    },
                    // Creating new TextPool takes under a second on a modern laptop.
                    10,
                    SECONDS);
            textPool = newTextPool;
        }
        return textPool;
    }
}
