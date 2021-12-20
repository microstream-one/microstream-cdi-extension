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

import one.microstream.cdi.test.CDIExtension;
import org.eclipse.microprofile.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.cache.configuration.Factory;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@CDIExtension
class CachePropertiesTest {

    @Inject
    private Config config;

    @Test
    @DisplayName("Should get the default value in the storeByValue")
    public void shouldReturnStoreByValue() {
        boolean storeByValue = CacheProperties.getStoreByValue(config);
        Assertions.assertFalse(storeByValue);
    }

    @Test
    @DisplayName("Should get storeByValue from Eclipse MicroProfile Config")
    public void shouldReturnStoreByValueConfig() {
        System.setProperty(CacheProperties.CACHE_STORE_VALUE.get(), "false");
        Assertions.assertFalse(CacheProperties.getStoreByValue(config));
        System.setProperty(CacheProperties.CACHE_STORE_VALUE.get(), "true");
        Assertions.assertTrue(CacheProperties.getStoreByValue(config));
        System.clearProperty(CacheProperties.CACHE_STORE_VALUE.get());
    }

    @Test
    @DisplayName("Should get the default value in the writeThrough")
    public void shouldReturnWriteThrough() {
        boolean storeByValue = CacheProperties.getWriteThrough(config);
        Assertions.assertFalse(storeByValue);
    }

    @Test
    @DisplayName("Should get writeThrough from Eclipse MicroProfile Config")
    public void shouldReturnSWriteThroughConfig() {
        System.setProperty(CacheProperties.CACHE_WRITE_THROUGH.get(), "false");
        Assertions.assertFalse(CacheProperties.getWriteThrough(config));
        System.setProperty(CacheProperties.CACHE_WRITE_THROUGH.get(), "true");
        Assertions.assertTrue(CacheProperties.getWriteThrough(config));
        System.clearProperty(CacheProperties.CACHE_WRITE_THROUGH.get());
    }

    @Test
    @DisplayName("Should get the default value in the readThrough")
    public void shouldReturnReadThrough() {
        boolean storeByValue = CacheProperties.getReadThrough(config);
        Assertions.assertFalse(storeByValue);
    }

    @Test
    @DisplayName("Should get readThrough from Eclipse MicroProfile Config")
    public void shouldReturnReadThroughConfig() {
        System.setProperty(CacheProperties.CACHE_READ_THROUGH.get(), "false");
        Assertions.assertFalse(CacheProperties.getReadThrough(config));
        System.setProperty(CacheProperties.CACHE_READ_THROUGH.get(), "true");
        Assertions.assertTrue(CacheProperties.getReadThrough(config));
        System.clearProperty(CacheProperties.CACHE_READ_THROUGH.get());
    }

    @Test
    @DisplayName("Should get the default value in the managementEnabled")
    public void shouldReturnManagementEnabled() {
        boolean storeByValue = CacheProperties.getManagementEnabled(config);
        Assertions.assertFalse(storeByValue);
    }

    @Test
    @DisplayName("Should get managementEnabled from Eclipse MicroProfile Config")
    public void shouldReturnManagementEnabledConfig() {
        System.setProperty(CacheProperties.CACHE_MANAGEMENT.get(), "false");
        Assertions.assertFalse(CacheProperties.getManagementEnabled(config));
        System.setProperty(CacheProperties.CACHE_MANAGEMENT.get(), "true");
        Assertions.assertTrue(CacheProperties.getManagementEnabled(config));
        System.clearProperty(CacheProperties.CACHE_MANAGEMENT.get());
    }

    @Test
    @DisplayName("Should get the default value in the statisticsEnabled")
    public void shouldReturnStatisticsEnabled() {
        boolean storeByValue = CacheProperties.getStatisticsEnabled(config);
        Assertions.assertFalse(storeByValue);
    }

    @Test
    @DisplayName("Should get statisticsEnabled from Eclipse MicroProfile Config")
    public void shouldReturnStatisticsEnabledConfig() {
        System.setProperty(CacheProperties.CACHE_STATISTICS.get(), "false");
        Assertions.assertFalse(CacheProperties.getStatisticsEnabled(config));
        System.setProperty(CacheProperties.CACHE_STATISTICS.get(), "true");
        Assertions.assertTrue(CacheProperties.getStatisticsEnabled(config));
        System.clearProperty(CacheProperties.CACHE_STATISTICS.get());
    }

    @Test
    public void shouldReturnNullAsDefaultLoaderFactory() {
        Assertions.assertNull(CacheProperties.getLoaderFactory(config));
    }

    @Test
    public void shouldReturnNullAsDefaultWriterFactory() {
        Assertions.assertNull(CacheProperties.getWriterFactory(config));
    }

    @Test
    public void shouldReturnNullAsDefaultExpireFactory() {
        Assertions.assertNull(CacheProperties.getExpiryFactory(config));
    }

    @Test
    public void shouldReturnErrorWhenIsNotFactoryInstance() {
        System.setProperty(CacheProperties.CACHE_LOADER_FACTORY.get(), "java.lang.String");
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> Assertions.assertNull(CacheProperties.getLoaderFactory(config)));
        System.clearProperty(CacheProperties.CACHE_LOADER_FACTORY.get());
    }

    @Test
    public void shouldCreateLoaderFactory() {
        System.setProperty(CacheProperties.CACHE_LOADER_FACTORY.get(), MockLoaderFactory.class.getName());
        Factory<CacheLoader<Object, Object>> loaderFactory = CacheProperties.getLoaderFactory(config);
        Assertions.assertTrue(loaderFactory instanceof MockLoaderFactory);
        System.clearProperty(CacheProperties.CACHE_LOADER_FACTORY.get());
    }

    @Test
    public void shouldCreateCacheWriterFactory() {
        System.setProperty(CacheProperties.CACHE_WRITER_FACTORY.get(), MockCacheWriter.class.getName());
        Factory<CacheWriter<Object, Object>> loaderFactory = CacheProperties.getWriterFactory(config);
        Assertions.assertTrue(loaderFactory instanceof MockCacheWriter);
        System.clearProperty(CacheProperties.CACHE_WRITER_FACTORY.get());
    }

    @Test
    public void shouldCreateExpireFactory() {
        System.setProperty(CacheProperties.CACHE_EXPIRES_FACTORY.get(), MockExpiryPolicy.class.getName());
        Factory<ExpiryPolicy> loaderFactory = CacheProperties.getExpiryFactory(config);
        Assertions.assertTrue(loaderFactory instanceof MockExpiryPolicy);
        System.clearProperty(CacheProperties.CACHE_EXPIRES_FACTORY.get());
    }
}