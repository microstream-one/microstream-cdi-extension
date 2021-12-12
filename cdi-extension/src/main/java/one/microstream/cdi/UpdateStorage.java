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

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * An interceptor annotation to update the Root in the Microstream.
 * Stores the registered root instance (as returned by {@link one.microstream.storage.types.StorageManager#root()})
 * by using the default storing logic
 * by calling {@link one.microstream.storage.types.StorageManager#createStorer()}
 * to create the {@link one.microstream.persistence.types.Storer} to be used.<br>
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface UpdateStorage {
}
