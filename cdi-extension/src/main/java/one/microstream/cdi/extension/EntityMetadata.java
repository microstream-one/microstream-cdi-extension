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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

class EntityMetadata {

    private final Class<?> type;
    private List<FieldMetadata> fields;

    private EntityMetadata(Class<?> type, List<FieldMetadata> fields) {
        this.type = type;
        this.fields = fields;
    }

    public List<FieldMetadata> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public Stream<Object> values(Object entity) {
        Objects.requireNonNull(entity, "entity is required");
        if (!type.equals(entity.getClass())) {
            throw new IllegalArgumentException(String.format("The entity %s is not compatible with the metadata %s"
                    , entity.getClass(), type));
        }
        return fields.stream()
                .map(f -> f.read(entity))
                .filter(Objects::nonNull);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityMetadata that = (EntityMetadata) o;
        return Objects.equals(type, that.type) && Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, fields);
    }

    static <T> EntityMetadata of(Class<T> entity) {
        List<FieldMetadata> fields = new ArrayList<>();
        for (Field field : entity.getDeclaredFields()) {
            Class<?> type = field.getType();
            if (isLazyFields(type)) {
                field.setAccessible(true);
                fields.add(FieldMetadata.of(field));
            }
        }
        return new EntityMetadata(entity, fields);
    }

    /**
     * Returns if the is lazy, so is {@link  Iterable} and {@link  Map} type.
     *
     * @param type the entity type
     * @return if it is a Lazy field or not
     * @see one.microstream.cdi.StoreType
     */
    private static boolean isLazyFields(Class<?> type) {
        return Iterable.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }
}
