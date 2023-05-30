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
package jakartaee.examples.ejb.securedremoteejb;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;

/**
 * A secured remote stateless session bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless(name = "securedRemoteEjb")
public class SecuredRemoteEjbBean implements SecuredRemoteEjb {

    /**
     * Hello world!
     * 
     * @return "Hello World!"
     */
    @Override
    @RolesAllowed(value = {"user"})
    public String helloWorld() {
        return "Hello World!";
    }    
}
