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

package one.microstream.helidon;

import one.microstream.cdi.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Storage
public class Items {

    private Set<Item> data = new HashSet<>();

    public void add(Item item) {
        Objects.requireNonNull(item, "item is required");
        this.data.add(item);
    }

    public Set<Item> getData() {
        return Collections.unmodifiableSet(data);
    }

    public Optional<Item> findById(String id) {
        Objects.requireNonNull(id, "id is required");
        return data.stream().filter(i -> i.getName().equals(id)).findFirst();
    }

    public void deleteById(String id) {
        Objects.requireNonNull(id, "id is required");
        this.data.removeIf(i -> i.getName().equals(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Items items = (Items) o;
        return Objects.equals(data, items.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }

    @Override
    public String toString() {
        return "Items{" +
                "data=" + data +
                '}';
    }

}