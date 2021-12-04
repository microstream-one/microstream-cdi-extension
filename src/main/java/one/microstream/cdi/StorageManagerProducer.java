package one.microstream.cdi;

import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
class StorageManagerProducer {

    private static final String DEFAULT_CONFIGURATION = "microstream";

    @Produces
    @ApplicationScoped
    public StorageManager getStoreManager() {
        Config config = ConfigProvider.getConfig();
        return config.getValue(DEFAULT_CONFIGURATION, StorageManager.class);
    }

    public void dispose(@Disposes StorageManager manager) {
        manager.close();
    }
}
