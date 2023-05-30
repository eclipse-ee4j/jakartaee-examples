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
package jakarta.tutorial.simplegreeting;

import jakarta.enterprise.context.Dependent;

@Informal
@Dependent
public class InformalGreeting extends Greeting {
    
    @Override
    public String greet(String name) {
        return "Hi, " + name + "!";
    }
}
