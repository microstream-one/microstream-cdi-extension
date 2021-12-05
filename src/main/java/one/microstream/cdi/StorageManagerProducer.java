/*
 *  Copyright 2021 Otavio Santana
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package one.microstream.cdi;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfigurationBuilder;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.util.logging.Logger;

@ApplicationScoped
class StorageManagerProducer {

    private static final Logger LOGGER = Logger.getLogger(StorageManagerProducer.class.getName());

    private static final String DEFAULT_CONFIGURATION = "META-INF/microprofile-config.properties";

    @Produces
    @ApplicationScoped
    public StorageManager getStoreManager() {
        LOGGER.info("Loading default StorageManager loading from MicroProfile Config file: " +
                DEFAULT_CONFIGURATION);
        return EmbeddedStorageConfiguration.load(DEFAULT_CONFIGURATION)
                .createEmbeddedStorageFoundation()
                .createEmbeddedStorageManager().start();
    }

    public void dispose(@Disposes StorageManager manager) {
        LOGGER.info("Closing the default StorageManager");
        manager.close();
    }
}
