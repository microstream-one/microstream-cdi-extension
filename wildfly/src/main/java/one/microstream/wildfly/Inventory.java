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

package one.microstream.payara;


import one.microstream.cdi.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;


@Storage
public class Inventory {

    private final Set<Product> products = new HashSet<>();

    public void add(Product product) {
        Objects.requireNonNull(product, "product is required");
        this.products.add(product);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Optional<Product> findById(long id) {
        return this.products
                .stream()
                .filter(isIdEquals(id))
                .limit(1)
                .findFirst();
    }

    public void deleteById(long id) {
        this.products.removeIf(isIdEquals(id));

    }

    private Predicate<Product> isIdEquals(long id) {
        return p -> p.getId() == id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }


}
