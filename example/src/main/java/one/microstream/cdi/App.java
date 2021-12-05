package one.microstream.cdi;

import one.microstream.storage.types.StorageManager;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class App {

    public static void main(String[] args) {
        try(SeContainer container = SeContainerInitializer.newInstance().initialize()){
            StorageManager manager = container.select(StorageManager.class).get();

        }
    }
}
