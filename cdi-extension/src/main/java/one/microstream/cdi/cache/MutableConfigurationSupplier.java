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

import org.eclipse.microprofile.config.Config;

import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Create a Parser to explore the benefits of Eclipse MicroProfile Configuration
 *
 * @param <K> the key type in the cache
 * @param <V> the value type in the cache
 */
class MutableConfigurationSupplier<K, V> implements Supplier<MutableConfiguration<K, V>> {

    private final StorageCacheProperty<K, V> cacheProperty;

    private final boolean storeByValue;

    private final boolean writeThrough;

    private final boolean readThrough;

    private final boolean managementEnabled;

    private final boolean statisticsEnabled;

    private final Factory<CacheLoader<K, V>> loaderFactory;

    private final Factory<CacheWriter<K, V>> writerFactory;

    private final Factory<ExpiryPolicy> expiryFactory;

    private MutableConfigurationSupplier(StorageCacheProperty<K, V> cacheProperty, boolean storeByValue,
                                         boolean writeThrough, boolean readThrough,
                                         boolean managementEnabled, boolean statisticsEnabled,
                                         Factory<CacheLoader<K, V>> loaderFactory,
                                         Factory<CacheWriter<K, V>> writerFactory,
                                         Factory<ExpiryPolicy> expiryFactory) {
        this.cacheProperty = cacheProperty;
        this.storeByValue = storeByValue;
        this.writeThrough = writeThrough;
        this.readThrough = readThrough;
        this.managementEnabled = managementEnabled;
        this.statisticsEnabled = statisticsEnabled;
        this.loaderFactory = loaderFactory;
        this.writerFactory = writerFactory;
        this.expiryFactory = expiryFactory;
    }

    public static <K, V> MutableConfigurationSupplier<K, V> of(StorageCacheProperty<K, V> cacheProperty,
                                                               Config config) {
        boolean storeByValue = CacheProperties.getStoreByValue(config);
        boolean writeThrough = CacheProperties.getWriteThrough(config);
        boolean readThrough = CacheProperties.getReadThrough(config);
        boolean managementEnabled = CacheProperties.getManagementEnabled(config);
        boolean statisticsEnabled = CacheProperties.getStatisticsEnabled(config);
        Factory<CacheLoader<K, V>> loaderFactory = CacheProperties.getLoaderFactory(config);
        Factory<CacheWriter<K, V>> writerFactory = CacheProperties.getWriterFactory(config);
        Factory<ExpiryPolicy> expiryFactory = CacheProperties.getExpiryFactory(config);

        return new MutableConfigurationSupplier<>(cacheProperty, storeByValue, writeThrough,
                readThrough, managementEnabled, statisticsEnabled,
                loaderFactory, writerFactory, expiryFactory);
    }

    @Override
    public MutableConfiguration<K, V> get() {
        Class<K> key = cacheProperty.getKey();
        Class<V> value = cacheProperty.getValue();
        MutableConfiguration<K, V> configuration = new MutableConfiguration<>();
        configuration.setTypes(key, value);
        configuration.setStoreByValue(storeByValue)
                .setWriteThrough(writeThrough)
                .setReadThrough(readThrough)
                .setManagementEnabled(managementEnabled)
                .setStatisticsEnabled(statisticsEnabled);

        if (Objects.nonNull(loaderFactory)) {
            configuration.setCacheLoaderFactory(loaderFactory);
        }
        if (Objects.nonNull(writerFactory)) {
            configuration.setCacheWriterFactory(writerFactory);
        }
        if (Objects.nonNull(expiryFactory)) {
            configuration.setExpiryPolicyFactory(expiryFactory);
        }
        return configuration;
    }

    @Override
    public String toString() {
        return "{" +
                "cacheProperty=" + cacheProperty +
                ", storeByValue=" + storeByValue +
                ", writeThrough=" + writeThrough +
                ", readThrough=" + readThrough +
                ", managementEnabled=" + managementEnabled +
                ", statisticsEnabled=" + statisticsEnabled +
                ", loaderFactory=" + loaderFactory +
                ", writerFactory=" + writerFactory +
                ", expiryFactory=" + expiryFactory +
                '}';
    }
}
