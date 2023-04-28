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

import java.util.logging.Logger;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

/**
 *
 * @author ian
 */
public class HelloInterceptor {
    protected String greeting;
    private static final Logger logger = Logger.getLogger("interceptor.ejb.HelloInterceptor");

    public HelloInterceptor() {
    }

    @AroundInvoke
    public Object modifyGreeting(InvocationContext ctx) throws Exception {
        Object[] parameters = ctx.getParameters();
        String param = (String) parameters[0];
        param = param.toLowerCase();
        parameters[0] = param;
        ctx.setParameters(parameters);
        try {
            return ctx.proceed();
        } catch (Exception e) {
            logger.warning("Error calling ctx.proceed in modifyGreeting()");
            return null;
        }
    }

}
