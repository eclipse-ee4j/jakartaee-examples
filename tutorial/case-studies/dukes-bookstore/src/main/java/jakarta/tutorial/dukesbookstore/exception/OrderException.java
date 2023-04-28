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
 * <p>This application exception indicates that an order cannot be
 * completed.</p>
 */
public class OrderException extends Exception {

    private static final long serialVersionUID = -5058707185180716794L;

    public OrderException() {
    }

    public OrderException(String msg) {
        super(msg);
    }
}
