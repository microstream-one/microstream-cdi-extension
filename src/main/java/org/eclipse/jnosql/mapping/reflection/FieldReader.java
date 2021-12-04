/*
 *  Copyright (c) 2020 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.eclipse.jnosql.mapping.reflection;


/**
 * A piece of operations within a class.
 * This class does the getter operation in a {@link java.lang.reflect.Field} in a class from a Field.
 */
public interface FieldReader {

    /**
     * Reads the entity bean, it will read the respective field and return the value.
     *
     * @param bean the entity that has the field
     * @return the field value from the entity
     * @throws NullPointerException when bean is null
     */
    Object read(Object bean);
}
