/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.trading.rar.outbound;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.resource.ResourceException;
import jakarta.tutorial.trading.rar.api.TradeConnection;
import jakarta.tutorial.trading.rar.api.TradeOrder;
import jakarta.tutorial.trading.rar.api.TradeProcessingException;
import jakarta.tutorial.trading.rar.api.TradeResponse;

/* An application-level connection handle used by clients to access
 * the physical connection. The physical connection is represented by
 * a ManagedConnection instance */
public class TradeConnectionImpl implements TradeConnection {
    
    private static final Logger log = Logger.getLogger("TradeConnectionImpl");
    private TradeManagedConnection mconnection;
    private boolean valid;
    
    /* */
    TradeConnectionImpl(TradeManagedConnection mconnection) {
        this.mconnection = mconnection;
        valid = true;
    }
    
    /* Called by the managed connection to dis/associate this handle. */
    TradeManagedConnection getManagedConnection() {
        return mconnection;
    }
    void setManagedConnection(TradeManagedConnection mconnection) {
        this.mconnection = mconnection;
    }
    
    /* Called by the managed connection to invalidate this handle */
    void invalidate() {
        valid = false;
    }

    /* Submits a trade order to the EIS */
    @Override
    public TradeResponse submitOrder(TradeOrder order) 
                                     throws TradeProcessingException {
        log.info("[TradeConnectionImpl] submitOrder()");
        if (valid) {
            try {
                String resp = mconnection.sendCommandToEIS(order.toString());
                return new TradeResponse(resp);
            } catch (IOException e) {
                throw new TradeProcessingException(e.getMessage());
            }
        } else
            throw new TradeProcessingException("Connection handle is invalid");
    }
    
    /* Closes the connection handle */
    @Override
    public void close() throws ResourceException {
        log.info("[TradeConnectionImpl] close()");
        valid = false;
        mconnection.disassociateConnection();
    }
    
}
