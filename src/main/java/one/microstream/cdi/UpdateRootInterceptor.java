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

package one.microstream.cdi;

import one.microstream.concurrency.XThreads;
import one.microstream.storage.types.StorageManager;

import javax.annotation.Priority;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * The implementation of the {@link UpdateRoot} using CDI interceptor
 */
@UpdateRoot
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
class UpdateRootInterceptor {

    private static final Logger LOGGER = Logger.getLogger(UpdateRootInterceptor.class.getName());

    @Inject
    private Instance<StorageManager> managerInstance;

    @AroundInvoke
    public Object auditMethod(InvocationContext ctx) throws Exception {

        Object result = ctx.proceed();
        XThreads.executeSynchronized(() -> {
            StorageManager manager = managerInstance.get();
            long storeRoot = manager.storeRoot();
            String message = String.format("Saving the state of root at %s in the method %s, storeRoot id %d",
                    ctx.getTarget().getClass(), ctx.getMethod().getName(), storeRoot);
            LOGGER.info(message);
        });

        return result;
    }
}
