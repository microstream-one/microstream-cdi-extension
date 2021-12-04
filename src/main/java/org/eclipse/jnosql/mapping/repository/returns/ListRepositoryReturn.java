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

import org.eclipse.jnosql.mapping.repository.DynamicReturn;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListRepositoryReturn extends AbstractRepositoryReturn {

    public ListRepositoryReturn() {
        super(null);
    }

    @Override
    public boolean isCompatible(Class<?> entityClass, Class<?> returnType) {
        return List.class.equals(returnType)
                || Iterable.class.equals(returnType)
                || Collection.class.equals(returnType);
    }

    @Override
    public <T> Object convert(DynamicReturn<T> dynamicReturn) {
        return dynamicReturn.result().collect(Collectors.toList());
    }

    @Override
    public <T> Object convertPageable(DynamicReturn<T> dynamicReturn) {
        return dynamicReturn.streamPagination().collect(Collectors.toList());
    }
}
