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
package one.microstream.cdi;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

@Storage
public class Names implements Supplier<Set<String>> {

    private final Set<String> names = new HashSet<>();

    public void add(String name) {
        this.names.add(Objects.requireNonNull(name, "name is required"));
    }

    @Override
    public Set<String> get() {
        return Collections.unmodifiableSet(names);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Names names = (Names) o;
        return Objects.equals(this.names, names.names);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(names);
    }

    @Override
    public String toString() {
        return "Names{" +
                "names=" + names +
                '}';
    }
}
