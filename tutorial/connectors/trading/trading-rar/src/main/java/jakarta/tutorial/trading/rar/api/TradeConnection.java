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

import jakarta.resource.ResourceException;

public interface TradeConnection {

    /* Submits a trade order to the EIS */
    public TradeResponse submitOrder(TradeOrder order) 
                                     throws TradeProcessingException;
    /* Closes the connection handle */
    public void close() throws ResourceException;
    
}
