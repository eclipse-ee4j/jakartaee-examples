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
package jakarta.tutorial.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

@Decorator
public abstract class CoderDecorator implements Coder {
    
    @Inject
    @Delegate
    @Any
    Coder coder;
    
    @Override
    public String codeString(String s, int tval) {
        int len = s.length();
 
        return "\"" + s + "\" becomes " + "\"" + coder.codeString(s, tval) 
                + "\", " + len + " characters in length";
    }
}
