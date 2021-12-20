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
        Assertions.assertFalse(CacheProperties.getStoreByValue(config));
        System.setProperty(CacheProperties.CACHE_WRITE_THROUGH.get(), "true");
        Assertions.assertTrue(CacheProperties.getStoreByValue(config));
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
        Assertions.assertFalse(CacheProperties.getStoreByValue(config));
        System.setProperty(CacheProperties.CACHE_READ_THROUGH.get(), "true");
        Assertions.assertTrue(CacheProperties.getStoreByValue(config));
        System.clearProperty(CacheProperties.CACHE_READ_THROUGH.get());
    }
}