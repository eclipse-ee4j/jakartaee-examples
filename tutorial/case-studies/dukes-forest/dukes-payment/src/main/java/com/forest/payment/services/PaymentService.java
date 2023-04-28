/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.forest.payment.services;

import java.util.logging.Logger;

import com.forest.events.OrderEvent;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/pay")
public class PaymentService {

    private static final Logger logger = Logger.getLogger("PaymentService");
    
    public PaymentService() { }
    
    @POST
    @Consumes("application/xml")
    public Response processPayment(OrderEvent order) {
        logger.info("Amount: "+order.getAmount());
        if (order.getAmount() < 1000) {
            return Response.ok().build();
        } else {
            return Response.status(401).build();
        }
    }
    
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "PaymentService";
    }
}
