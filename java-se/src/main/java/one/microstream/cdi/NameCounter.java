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

package one.microstream.cdi;

import one.microstream.cdi.cache.StorageCache;

import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class NameCounter {

    @Inject
    @StorageCache
    private Cache<String, Integer> counter;

    @Store
    public synchronized int count(String name) {
        int counter = show(name);
        counter++;
        this.counter.put(name, counter);
        return counter;
    }

    public int show(String name) {
        return ofNullable(this.counter.get(name)).orElse(0);
    }

    public Map<String, Integer> getNames() {
        Map<String, Integer> map = new HashMap<>();
        counter.forEach(c -> map.put(c.getKey(), c.getValue()));
        return map;
    }
}
