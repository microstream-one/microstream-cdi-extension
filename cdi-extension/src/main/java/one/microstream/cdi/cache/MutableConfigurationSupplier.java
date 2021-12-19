/*
 *    Copyright 2021 Otavio Santana
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package one.microstream.cdi.cache;

import javax.cache.configuration.MutableConfiguration;
import java.util.function.Supplier;

/**
 * Create a Parser to explore the benefits of Eclipse MicroProfile Configuration
 * @param <K> the key type in the cache
 * @param <V> the value type in the cache
 */
class MutableConfigurationSupplier<K,V> implements Supplier<MutableConfiguration<K, V>> {

    private StorageCacheProperty<K, V> cacheProperty;


    @Override
    public MutableConfiguration<K, V> get() {
        return null;
    }
}
