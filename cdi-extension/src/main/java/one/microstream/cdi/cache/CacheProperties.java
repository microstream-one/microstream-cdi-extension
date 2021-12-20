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

import one.microstream.cdi.MicrostreamException;
import one.microstream.cdi.extension.ConstructorUtil;
import org.eclipse.microprofile.config.Config;

import javax.cache.configuration.Factory;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * The relation with the properties from Microstream docs:
 * https://docs.microstream.one/manual/cache/configuration/properties.html
 */
public enum CacheProperties implements Supplier<String> {

    /**
     * cacheLoaderFactory - A CacheLoader should be configured
     * for "Read Through" caches to load values when a cache miss occurs.
     */
    CACHE_LOADER_FACTORY("microstream.cache.loader.factory"),
    /**
     * cacheWriterFactory - A CacheWriter is used for write-through to an external resource.
     */
    CACHE_WRITER_FACTORY("microstream.cache.writer.factory"),
    /**
     * expiryPolicyFactory - Determines when cache entries will expire based on creation,
     * access and modification operations.
     */
    CACHE_EXPIRES_FACTORY("microstream.cache.expires.factory"),
    /**
     * readThrough - When in "read-through" mode, cache misses that occur due to cache entries not existing
     * as a result of performing a "get" will appropriately cause the configured CacheLoader to be invoked.
     */
    CACHE_READ_THROUGH("microstream.cache.read.through"),
    /**
     * writeThrough - When in "write-through" mode, cache updates that occur as a result
     * of performing "put" operations will appropriately cause the configured CacheWriter to be invoked.
     */
    CACHE_WRITE_THROUGH("microstream.cache.write.through"),
    /**
     * storeByValue - When a cache is storeByValue,
     * any mutation to the key or value does not affect the key of value stored in the cache.
     */
    CACHE_STORE_VALUE("microstream.cache.store.value"),
    /**
     * statisticsEnabled - Checks whether statistics collection is enabled in this cache.
     */
    CACHE_STATISTICS("microstream.cache.statistics"),
    /**
     * managementEnabled - Checks whether management is enabled on this cache.
     */
    CACHE_MANAGEMENT("microstream.cache.management");

    private final String value;

    CacheProperties(String value) {
        this.value = value;
    }

    public static boolean getStoreByValue(Config config) {
        return getBoolean(config, CACHE_STORE_VALUE);
    }

    public static boolean getWriteThrough(Config config) {
        return getBoolean(config, CACHE_WRITE_THROUGH);
    }

    public static boolean getReadThrough(Config config) {
        return getBoolean(config, CACHE_READ_THROUGH);
    }

    public static boolean getManagementEnabled(Config config) {
        return getBoolean(config, CACHE_MANAGEMENT);
    }

    public static boolean getStatisticsEnabled(Config config) {
        return getBoolean(config, CACHE_STATISTICS);
    }

    private static Boolean getBoolean(Config config, CacheProperties cacheStatistics) {
        return config.getOptionalValue(cacheStatistics.get(), boolean.class).orElse(false);
    }

    public static <V, K> Factory<CacheLoader<K, V>> getLoaderFactory(Config config) {
        String factoryClass = config.getOptionalValue(CACHE_LOADER_FACTORY.get(), String.class).orElse("");
        return getFactoryClass(factoryClass);
    }


    public static <V, K> Factory<CacheWriter<K, V>> getWriterFactory(Config config) {
        String factoryClass = config.getOptionalValue(CACHE_WRITER_FACTORY.get(), String.class).orElse("");
        return getFactoryClass(factoryClass);
    }

    public static Factory<ExpiryPolicy> getExpiryFactory(Config config) {
        String factoryClass = config.getOptionalValue(CACHE_EXPIRES_FACTORY.get(), String.class).orElse("");

        return getFactoryClass(factoryClass);
    }

    private static <T> T getFactoryClass(String className) {
        if (className.isBlank()) {
            return null;
        }
        try {
            Class<T> factory = (Class<T>) Class.forName(className);
            T instance = ConstructorUtil.create(factory);
            if (instance instanceof Factory) {
                return instance;
            }
            throw new IllegalArgumentException("The instance class must be a " + Factory.class.getName()
                    + " implementation");
        } catch (ClassNotFoundException e) {
            throw new MicrostreamException("There is an issue to load the class: " + className, e);
        }
    }

    @Override
    public String get() {
        return value;
    }
}
