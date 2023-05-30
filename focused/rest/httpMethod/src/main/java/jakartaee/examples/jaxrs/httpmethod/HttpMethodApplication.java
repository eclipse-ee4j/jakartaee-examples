/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakartaee.examples.jaxrs.httpmethod;

import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * The application for the @HttpMethod example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationPath("rest")
public class HttpMethodApplication extends Application {

    /**
     * Get the classes.
     * 
     * @return the classes.
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet();
        classes.add(HttpMethodResource.class);
        return classes;
    }
}
