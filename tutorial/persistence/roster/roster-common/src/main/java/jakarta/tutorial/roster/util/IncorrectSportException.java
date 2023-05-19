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
package jakarta.tutorial.roster.util;

public class IncorrectSportException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>IncorrectSportException</code> without detail message.
     */
    public IncorrectSportException() {
    }
    
    
    /**
     * Constructs an instance of <code>IncorrectSportException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IncorrectSportException(String msg) {
        super(msg);
    }
}
