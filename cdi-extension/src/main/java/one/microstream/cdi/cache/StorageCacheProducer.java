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

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;


@ApplicationScoped
class StorageCacheProducer {

    public static final String CACHE_PROVIDER = "one.microstream.cache.types.CachingProvider";

    @Produces
    @StorageCache
    public Cache<Integer,String> producer(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        CachingProvider provider = Caching.getCachingProvider(CACHE_PROVIDER);
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration<Integer, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(Integer.class, String.class)
                .setStoreByValue(false)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
        Cache<Integer, String> cache = cacheManager.createCache("jCache", configuration);
        return cache;
    }
}
