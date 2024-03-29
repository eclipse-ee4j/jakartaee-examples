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

/**
 * Implementation of the Coder interface that shifts letters in a string.
 */
public class CoderImpl implements Coder {

    /**
     * Shifts an input string by the number of characters
     * specified in the second argument.
     *
     * @param s     the input string
     * @param tval  the number of characters to shift
     * @return      the transformed string
     */
    @Logged
    @Override
    public String codeString(String s, int tval) {
        final int SPACE_CHAR = 32;
        final int CAPITAL_A = 65;
        final int CAPITAL_Z = 90;
        final int SMALL_A = 97;
        final int SMALL_Z = 122;

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {

            int cp = sb.codePointAt(i);
            int cplus = cp + tval;
            if (cp == SPACE_CHAR) //space
            {
                cplus = SPACE_CHAR;
            }
            if ((cp >= CAPITAL_A) && (cp <= CAPITAL_Z)) { //uppercase
                if (cplus > CAPITAL_Z) {
                    cplus = cplus - 26;
                }
            } else if ((cp >= SMALL_A) && (cp <= SMALL_Z)) { //lowercase
                if (cplus > SMALL_Z) {
                    cplus = cplus - 26;
                }
            } else { // punctuation, etc.
                cplus = cp;
            }
            char c = (char) cplus;
            sb.setCharAt(i, c);
        }

        return (sb.toString());

    }
}
