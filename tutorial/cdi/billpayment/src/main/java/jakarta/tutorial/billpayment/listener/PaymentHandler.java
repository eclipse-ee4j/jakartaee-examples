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
package jakarta.tutorial.billpayment.listener;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Observes;
import jakarta.tutorial.billpayment.event.PaymentEvent;
import jakarta.tutorial.billpayment.interceptor.Logged;
import jakarta.tutorial.billpayment.payment.Credit;
import jakarta.tutorial.billpayment.payment.Debit;

/**
 * Handler for the two kinds of PaymentEvent.
 */
@Logged
@SessionScoped
public class PaymentHandler implements Serializable {

    private static final Logger logger =
            Logger.getLogger(PaymentHandler.class.getCanonicalName());
    private static final long serialVersionUID = 2013564481486393525L;

    public PaymentHandler() {
        logger.log(Level.INFO, "PaymentHandler created.");
    }

    public void creditPayment(@Observes @Credit PaymentEvent event) {
        logger.log(Level.INFO, "PaymentHandler - Credit Handler: {0}",
                event.toString());

        // call a specific Credit handler class...
    }

    public void debitPayment(@Observes @Debit PaymentEvent event) {
        logger.log(Level.INFO, "PaymentHandler - Debit Handler: {0}",
                event.toString());
 
        // call a specific Debit handler class...
    }
}
