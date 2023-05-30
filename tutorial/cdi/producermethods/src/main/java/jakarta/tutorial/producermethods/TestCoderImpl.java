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
package jakarta.tutorial.producermethods;

/**
 * Coder implementation that does nothing but display the values of the
 * arguments.
 */
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
