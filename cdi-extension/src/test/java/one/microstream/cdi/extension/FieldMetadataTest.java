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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldMetadataTest {

    @Test
    public void shouldReturnIterable() {
        Inventory inventory = new Inventory("my inventory");
        inventory.add(new Product("Banana", "A fruit", 5));
        EntityMetadata metadata = EntityMetadata.of(Inventory.class);
        List<FieldMetadata> fields = metadata.getFields();
        FieldMetadata field = fields.get(0);
        Object read = field.read(inventory);
        Assertions.assertEquals(inventory.getProducts(), read);
    }

    public void shouldReturnMap() {

    }

    public void shouldReturnBoth() {

    }
}