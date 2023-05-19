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
package jakarta.tutorial.cartsecure.util;

public class BookException extends Exception {
    private static final long serialVersionUID = 6274585742564840905L;
    public BookException() {
    }

    public BookException(String msg) {
        super(msg);
    }
}
