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
package jakarta.tutorial.trading.rar.api;

/* Indicates that the trade order could not be processed */
public class TradeProcessingException extends Exception {

    public TradeProcessingException(String msg) {
        super(msg);
    }
}
