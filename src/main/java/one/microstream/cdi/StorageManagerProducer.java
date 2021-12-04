package one.microstream.cdi;

import one.microstream.storage.types.StorageManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import java.util.logging.Logger;

@ApplicationScoped
class StorageManagerProducer {

    private static final Logger LOGGER = Logger.getLogger(StorageManagerProducer.class.getName());


    public void close(@Disposes StorageManager storageManager) {
        LOGGER.info("Closing StorageManager connection");
        storageManager.close();
    }

}
