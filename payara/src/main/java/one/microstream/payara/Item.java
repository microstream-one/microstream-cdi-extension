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

package one.microstream.payara;

import one.microstream.payara.infra.FieldPropertyVisibilityStrategy;

import javax.json.bind.annotation.JsonbVisibility;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Item {

    private String name;

    private String description;
    private ItemType type;

    private LocalDate expires;

    private List<Ingredient> ingredients;


    public void update(Item item, RestaurantRepository repository) {
        this.description = item.description;
        this.expires = item.expires;
        this.type = item.type;
        this.name = item.name;
        this.ingredients = item.ingredients;
        repository.save(item);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public List<Ingredient> getIngredients() {
        if(Objects.isNull(this.ingredients)) {
            return Collections.emptyList();
        }
        return this.ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", expires=" + expires +
                '}';
    }
}