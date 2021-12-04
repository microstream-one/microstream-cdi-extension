package one.microstream.cdi;

import one.microstream.cdi.test.CDIExtension;
import one.microstream.storage.types.StorageManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@CDIExtension
public class StorageManagerConverterTest {

    @Inject
    @ConfigProperty
    private StorageManager manager;

    @Test
    public void shouldNotBeNull() {
        Assertions.assertNotNull(manager);
    }
}
