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

import static java.util.Optional.ofNullable;

@ApplicationScoped
public class NameCounter {

    @Inject
    @StorageCache
    private Cache<String, Integer> counter;

    public int count(String name) {
        int counter = ofNullable(this.counter.get(name)).orElse(0);
        counter++;
        this.counter.put(name, counter);
        return counter;
    }
}
