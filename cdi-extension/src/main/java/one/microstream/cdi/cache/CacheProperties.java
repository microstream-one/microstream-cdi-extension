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

import java.util.function.Supplier;

public enum CacheProperties implements Supplier<String> {
    CACHE_LOADER_FACTORY("microstream.cache.loader.factory"),
    CACHE_WRITER_FACTORY("microstream.cache.writer.factory"),
    CACHE_EXPIRES_FACTORY("microstream.cache.expires.factory"),
    CACHE_READ_THROUGH("microstream.cache.read.through"),
    CACHE_WRITE_THROUGH("microstream.cache.write.through"),
    CACHE_STORE_VALUE("microstream.cache.store.value"),
    CACHE_STATISTICS("microstream.cache.statistics"),
    CACHE_MANAGEMENT("microstream.cache.management");

    private final String value;

    CacheProperties(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
