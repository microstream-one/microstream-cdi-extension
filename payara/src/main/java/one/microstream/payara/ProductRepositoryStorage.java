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


import one.microstream.cdi.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class RestaurantRepositoryStorage implements RestaurantRepository {

    private static final Logger LOGGER = Logger.getLogger(RestaurantRepositoryStorage.class.getName());

    @Inject
    private Items items;

    @Override
    public Collection<Item> getAll() {
        return items.getData();
    }

    @Override
    @Store
    public Item save(Item item) {
        this.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> findById(String id) {
        LOGGER.info("Finding the item by id: " + id);
        return items.findById(id);
    }

    @Override
    @Store
    public void deleteById(String id) {
        items.deleteById(id);
    }
}
