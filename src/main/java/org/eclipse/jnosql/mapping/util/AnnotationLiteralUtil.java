/*
 *  Copyright (c) 2017 Otávio Santana and others
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
package org.eclipse.jnosql.mapping.util;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Utilitarian class to Annotation Literal
 */
public final class AnnotationLiteralUtil {

    /**
     * Annotation literal {@link Default}
     */
    public static final AnnotationLiteral<Default> DEFAULT_ANNOTATION = new AnnotationLiteral<Default>() {
    };

    /**
     * Annotation literal {@link Any}
     */
    public static final AnnotationLiteral<Any> ANY_ANNOTATION = new AnnotationLiteral<Any>() {
    };
    private AnnotationLiteralUtil() {
    }
}
