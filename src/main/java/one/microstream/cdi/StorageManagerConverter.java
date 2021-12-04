package one.microstream.cdi;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.spi.Converter;

/**
 * A Config converter to {@link StorageManager}
 */
public class StorageManagerConverter implements Converter<StorageManager> {

    @Override
    public StorageManager convert(String value) throws IllegalArgumentException, NullPointerException {
        EmbeddedStorageConfiguration.load();
        return null;
    }
}
