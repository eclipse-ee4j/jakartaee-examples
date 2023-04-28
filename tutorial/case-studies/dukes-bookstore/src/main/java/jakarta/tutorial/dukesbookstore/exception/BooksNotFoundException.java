/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.exception;

/**
 * <p>This application exception indicates that books have not been found.</p>
 */
public class BooksNotFoundException extends Exception {

    private static final long serialVersionUID = 4156679691884326238L;

    public BooksNotFoundException() {
    }

    public BooksNotFoundException(String msg) {
        super(msg);
    }
}
