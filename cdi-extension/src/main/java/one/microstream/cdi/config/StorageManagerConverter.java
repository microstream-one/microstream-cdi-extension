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

package one.microstream.cdi.config;

import one.microstream.configuration.types.Configuration;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfigurationBuilder;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.exceptions.StorageExceptionInitialization;
import one.microstream.storage.types.Database;
import one.microstream.storage.types.Databases;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * A Config converter to {@link StorageManager}
 */
public class StorageManagerConverter implements Converter<StorageManager> {

    private static final Logger LOGGER = Logger.getLogger(StorageManagerConverter.class.getName());

    private static final Map<String, StorageManager> MAP = new ConcurrentHashMap<>();

    // keep it on Eclipse MicroProfile
    //should I keep here or should I move it to Microstream
    // https://12factor.net/config
    //keep it as singleton
    @Override
    public StorageManager convert(String value) throws IllegalArgumentException, NullPointerException {
//        if (MAP.get(value) == null) {
//            synchronized (MAP) {
        LOGGER.info("Loading configuration to start the class StorageManager from the key: " + value);
        EmbeddedStorageConfigurationBuilder configurationBuilder = EmbeddedStorageConfiguration.load(value);
        EmbeddedStorageFoundation<?> embeddedStorageFoundation = configurationBuilder.createEmbeddedStorageFoundation();
        EmbeddedStorageManager embeddedStorageManager = embeddedStorageFoundation.createEmbeddedStorageManager();
        EmbeddedStorageManager manager = embeddedStorageManager.start();
        return manager;
//                MAP.put(value, manager);
//                return manager;
//            }
    }
}
