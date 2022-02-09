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
package one.microstream.cdi.extension;

import one.microstream.cdi.Store;
import one.microstream.concurrency.XThreads;
import one.microstream.storage.types.StorageManager;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Store
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
class StoreInterceptor {

    private static final Logger LOGGER = Logger.getLogger(StoreInterceptor.class.getName());

    @Inject
    private StorageManager manager;

    @AroundInvoke
    public Object store(InvocationContext context) throws Exception {
        LOGGER.log(Level.FINE, "Using Store operation in the " + context.getMethod());
        Object result = context.proceed();
        XThreads.executeSynchronized(() -> {
            Object root = manager.root();
            long store = manager.store(root);
            LOGGER.log(Level.FINE, "Store in the object id " + store);
        });

        return result;
    }
}
