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

package one.microstream.cdi.config;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfigurationPropertyNames;

/**
 * The relation with the properties from Microstream docs:
 * https://docs.microstream.one/manual/storage/configuration/properties.html
 */
enum ConfigurationCoreProperties {
    STORAGE_DIRECTORY("microstream.storage.directory", EmbeddedStorageConfigurationPropertyNames.STORAGE_DIRECTORY),
    STORAGE_FILESYSTEM("microstream.storage.filesystem", EmbeddedStorageConfigurationPropertyNames.STORAGE_FILESYSTEM),
    DELETION_DIRECTORY("microstream.deletion.directory", EmbeddedStorageConfigurationPropertyNames.DELETION_DIRECTORY),
    TRUNCATION_DIRECTORY("microstream.truncation.directory", EmbeddedStorageConfigurationPropertyNames.TRUNCATION_DIRECTORY),
    BACKUP_DIRECTORY("microstream.backup.directory", EmbeddedStorageConfigurationPropertyNames.BACKUP_DIRECTORY),
    BACKUP_FILESYSTEM("microstream.backup.filesystem", EmbeddedStorageConfigurationPropertyNames.BACKUP_FILESYSTEM),
    CHANNEL_COUNT("microstream.channel.count", EmbeddedStorageConfigurationPropertyNames.CHANNEL_COUNT),
    CHANNEL_DIRECTORY_PREFIX("microstream.channel.directory.prefix", EmbeddedStorageConfigurationPropertyNames.CHANNEL_DIRECTORY_PREFIX),
    DATA_FILE_PREFIX("microstream.data.file.prefix", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_PREFIX),
    DATA_FILE_SUFFIX("microstream.data.file.suffix", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_SUFFIX),
    TRANSACTION_FILE_PREFIX("microstream.transaction.file.prefix", EmbeddedStorageConfigurationPropertyNames.TRANSACTION_FILE_PREFIX),
    TRANSACTION_FILE_SUFFIX("microstream.transaction.file.suffix", EmbeddedStorageConfigurationPropertyNames.TRANSACTION_FILE_SUFFIX),
    TYPE_DICTIONARY_FILE_NAME("microstream.type.dictionary.file.name", EmbeddedStorageConfigurationPropertyNames.TYPE_DICTIONARY_FILE_NAME),
    RESCUED_FILE_SUFFIX("microstream.rescued.file.suffix", EmbeddedStorageConfigurationPropertyNames.RESCUED_FILE_SUFFIX),
    LOCK_FILE_NAME("microstream.lock.file.name", EmbeddedStorageConfigurationPropertyNames.LOCK_FILE_NAME),
    HOUSEKEEPING_INTERVAL("microstream.housekeeping.interval", EmbeddedStorageConfigurationPropertyNames.HOUSEKEEPING_INTERVAL),
    HOUSEKEEPING_TIME_BUDGET("microstream.housekeeping.time.budget", EmbeddedStorageConfigurationPropertyNames.HOUSEKEEPING_TIME_BUDGET),
    ENTITY_CACHE_THRESHOLD("microstream.entity.cache.threshold", EmbeddedStorageConfigurationPropertyNames.ENTITY_CACHE_THRESHOLD),
    ENTITY_CACHE_TIMEOUT("microstream.entity.cache.timeout", EmbeddedStorageConfigurationPropertyNames.ENTITY_CACHE_TIMEOUT),
    DATA_FILE_MINIMUM_SIZE("microstream.data.file.minimum.size", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_MINIMUM_SIZE),
    DATA_FILE_MAXIMUM_SIZE("microstream.data.file.maximum.size", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_MAXIMUM_SIZE),
    DATA_FILE_MINIMUM_USE_RATIO("microstream.data.file.minimum.use.ratio", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_MINIMUM_USE_RATIO),
    DATA_FILE_CLEANUP_HEAD_FILE("microstream.data.file.cleanup.head.file", EmbeddedStorageConfigurationPropertyNames.DATA_FILE_CLEANUP_HEAD_FILE);

    private final String microprofile;
    private final String microstream;

    ConfigurationCoreProperties(String microprofile, String microstream) {
        this.microprofile = microprofile;
        this.microstream = microstream;
    }

    public String getMicroprofile() {
        return microprofile;
    }

    public String getMicrostream() {
        return microstream;
    }
}
