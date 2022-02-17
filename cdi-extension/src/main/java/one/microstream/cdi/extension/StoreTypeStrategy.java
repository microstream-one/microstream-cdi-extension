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
package one.microstream.cdi.extension;

import one.microstream.cdi.MicrostreamException;
import one.microstream.cdi.Store;
import one.microstream.storage.types.StorageManager;

import java.util.logging.Level;
import java.util.logging.Logger;

enum StoreTypeStrategy implements StoreStrategy {

    EAGER {
        @Override
        public void store(Store store, StorageManager manager, StorageExtension extension) {
            Object root = manager.root();
            long storeId = manager.store(root);
            LOGGER.log(Level.WARNING, "Store the root it might return performance issue " + storeId);
        }
    }, LAZY {
        @Override
        public void store(Store store, StorageManager manager, StorageExtension extension) {

            Object root = manager.root();
            if (store.root()) {
                long storeId = manager.store(root);
                LOGGER.log(Level.WARNING, "Store the root it might return performance issue " + storeId);
            } else {
                EntityMetadata metadata = extension.get(root.getClass())
                        .orElseThrow(() -> new MicrostreamException("The entity metadata does" +
                                " not found to the related root class: " + root.getClass()));
                metadata.values(root, store.fields()).forEach(manager::store);
                LOGGER.log(Level.FINEST, "Storing Iterables and Maps fields from the root class "
                        + root.getClass() + " the fields: " + store.fields());
            }
        }
    };

    private static final Logger LOGGER = Logger.getLogger(StoreTypeStrategy.class.getName());
}
