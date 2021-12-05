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

import javax.enterprise.inject.Vetoed;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Vetoed
public class NameRoot {

    private Set<String> names = new HashSet<>();

    public void add(String name) {
        this.names.add(Objects.requireNonNull(name, "name is required"));
    }

    public Set<String> getNames() {
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
        NameRoot nameRoot = (NameRoot) o;
        return Objects.equals(names, nameRoot.names);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(names);
    }
}
