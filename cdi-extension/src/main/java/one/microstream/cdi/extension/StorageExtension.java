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
package one.microstream.cdi.extension;

import one.microstream.cdi.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class StorageExtension implements Extension {

    private final Set<Class<?>> storageRoot = new HashSet<>();

    public <T> void loadEntity(@Observes @WithAnnotations({Storage.class}) final ProcessAnnotatedType<T> target) {
        AnnotatedType<T> annotatedType = target.getAnnotatedType();
        if (annotatedType.isAnnotationPresent(Storage.class)) {
            Class<T> javaClass = target.getAnnotatedType().getJavaClass();
            storageRoot.add(javaClass);
        }
    }

    @Override
    public String toString() {
        return "StorageExtension{" +
                "storageRoot=" + storageRoot +
                '}';
    }
}
