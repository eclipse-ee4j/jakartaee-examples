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
package jakartaee.examples.jaxrs.helloworld;

import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * A request scoped bean for the JAX-RS HelloWorld example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationPath("rest")
public class HelloWorldApplication extends Application {

    /**
     * Get the classes.
     * 
     * @return the classes.
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet();
        classes.add(HelloWorldResource.class);
        return classes;
    }
}
