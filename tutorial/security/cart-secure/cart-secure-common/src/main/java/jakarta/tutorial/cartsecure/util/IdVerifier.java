/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.cartsecure.util;

public class IdVerifier {
    public IdVerifier() {
    }

    public boolean validate(String id) {
        boolean result = true;

        for (int i = 0; i < id.length(); i++) {
            if (Character.isDigit(id.charAt(i)) == false) {
                result = false;
            }
        }

        return result;
    }
}
