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
package org.eclipse.jnosql.mapping.repository.returns;

import jakarta.nosql.mapping.DynamicQueryException;
import org.eclipse.jnosql.mapping.repository.DynamicReturn;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SortedSetRepositoryReturn extends AbstractRepositoryReturn {

    public SortedSetRepositoryReturn() {
        super(null);
    }

    @Override
    public boolean isCompatible(Class<?> entityClass, Class<?> returnType) {
        return NavigableSet.class.equals(returnType)
                || SortedSet.class.equals(returnType);
    }

    @Override
    public <T> Object convert(DynamicReturn<T> dynamicReturn) {
        validate(dynamicReturn);
        return dynamicReturn.result().collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public <T> Object convertPageable(DynamicReturn<T> dynamicReturn) {
        validate(dynamicReturn);
        return dynamicReturn.streamPagination().collect(Collectors.toCollection(TreeSet::new));
    }

    public void validate(DynamicReturn<?> dynamicReturn) throws DynamicQueryException {

        Class<?> typeClass = dynamicReturn.typeClass();
        if (!Comparable.class.isAssignableFrom(typeClass)) {
            throw new DynamicQueryException(String.format("To use either NavigableSet or SortedSet the entity %s" +
                    " must implement Comparable.", typeClass));
        }
    }
}
