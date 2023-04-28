/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.billpayment.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.billpayment.event.PaymentEvent;
import jakarta.tutorial.billpayment.interceptor.Logged;
import jakarta.validation.constraints.Digits;

/**
 * Bean that fires DEBIT and CREDIT payment events based on UI selection.
 * Check server log output for event handling output.
 */
@Named
@SessionScoped
public class PaymentBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger(PaymentBean.class.getCanonicalName());
    private static final long serialVersionUID = 7130389273118012929L;

    @Inject
    @Credit
    Event<PaymentEvent> creditEvent;

    @Inject
    @Debit
    Event<PaymentEvent> debitEvent;

    private static final int DEBIT = 1;
    private static final int CREDIT = 2;    
    private int paymentOption = DEBIT;

    @Digits(integer = 10, fraction = 2, message = "Invalid value")
    private BigDecimal value;

    private Date datetime;

    /**
     * Fires a payment event.
     *
     * @return the response page location
     */
    @Logged
    public String pay() {
        this.setDatetime(Calendar.getInstance().getTime());
        switch (paymentOption) {
            case DEBIT:
                PaymentEvent debitPayload = new PaymentEvent();
                debitPayload.setPaymentType("Debit");
                debitPayload.setValue(value);
                debitPayload.setDatetime(datetime);
                debitEvent.fire(debitPayload);
                break;
            case CREDIT:
                PaymentEvent creditPayload = new PaymentEvent();
                creditPayload.setPaymentType("Credit");
                creditPayload.setValue(value);
                creditPayload.setDatetime(datetime);
                creditEvent.fire(creditPayload);
                break;
            default:
                logger.severe("Invalid payment option!");
        }
        return "response";
    }

    /**
     * Resets the values in the index page.
     */
    @Logged
    public void reset() {
        setPaymentOption(DEBIT);
        setValue(BigDecimal.ZERO);
    }

    public int getPaymentOption() {
        return this.paymentOption;
    }

    public void setPaymentOption(int paymentType) {
        this.paymentOption = paymentType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
