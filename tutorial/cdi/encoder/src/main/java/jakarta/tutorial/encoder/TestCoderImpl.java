/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.encoder;

import jakarta.enterprise.inject.Alternative;

/**
 * Coder implementation that does nothing but display the values of the
 * arguments.
 */
@Alternative
public class TestCoderImpl implements Coder {

   /**
    * Returns a string that displays the values of the arguments.
    *
    * @param s     the input string
    * @param tval  the number of characters to shift
    * @return      string displaying argument values
    */
    @Override
    public String codeString(String s, int tval) {
        return ("input string is " + s + ", shift value is " + tval);
    }
}
