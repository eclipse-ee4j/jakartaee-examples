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
package jakartaee.examples.ejb.remoteejb;

import jakarta.ejb.Stateless;

/**
 * A remote stateless session bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless(name = "remoteEjb")
public class RemoteEjbBean implements RemoteEjb {

    /**
     * Hello world!
     * 
     * @return "Hello World!"
     */
    @Override
    public String helloWorld() {
        return "Hello World!";
    }    
}
