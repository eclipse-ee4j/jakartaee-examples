/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jakarta.tutorial.interceptor.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.interceptor.Interceptors;

/**
 *
 * @author ian
 */
@Stateless
@Named
public class HelloBean {

    protected String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    @Interceptors(HelloInterceptor.class)
    public void setName(String name) {
        this.name = name;
    }

}
