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
package one.microstream.cdi.interceptor;

import one.microstream.cdi.test.CDIExtension;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@CDIExtension
class StoreInterceptorTest {


    @Inject
    private AgendaLazyService lazyService;

    @Inject
    private AgendaEagerService eagerService;

    @Inject
    private AgendaService3 service3;

    @Test
    public void shouldUpdateRootLazily() {
        lazyService.addNameRoot("Poliana");
    }

    @Test
    public void shouldUpdateFieldLazy() {
        lazyService.addNameLazy("Otavio");
    }

    @Test
    public void add() {
        eagerService.add("Ada");
    }

    @Test
    public void addEager() {
        eagerService.addEager("Ada");
    }

    @Test
    public void addSpecific() {
        service3.add("Ada");
    }

}
