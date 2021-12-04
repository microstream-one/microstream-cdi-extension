package one.microstream.cdi.config;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfigurationBuilder;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.logging.Logger;

/**
 * A Config converter to {@link StorageManager}
 */
public class StorageManagerConverter implements Converter<StorageManager> {

    private static final Logger LOGGER = Logger.getLogger(StorageManagerConverter.class.getName());

    @Override
    public StorageManager convert(String value) throws IllegalArgumentException, NullPointerException {
        LOGGER.info("Loading configuration to start the class StorageManager from the key: " + value);
        EmbeddedStorageConfigurationBuilder load = EmbeddedStorageConfiguration.load(value);
        EmbeddedStorageFoundation<?> embeddedStorageFoundation = load.createEmbeddedStorageFoundation();
        EmbeddedStorageManager embeddedStorageManager = embeddedStorageFoundation.createEmbeddedStorageManager();
        return embeddedStorageManager;
    }
}
