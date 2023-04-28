/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.shareddurableconsumer;

import java.util.concurrent.atomic.AtomicLong;

import jakarta.jms.JMSException;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * The TextListener class implements the MessageListener interface by defining
 * an onMessage method that displays the contents of a TextMessage.
 *
 * This class acts as the listener for the AsynchConsumer class.
 */
public class TextListener implements MessageListener {

    AtomicLong count = new AtomicLong(0);

    /**
     * Displays the message text.
     *
     * @param message the incoming message
     */
    @Override
    public void onMessage(Message m) {
        long i;

        try {
            if (m instanceof TextMessage) {
                i = count.incrementAndGet();
                // Comment out the following line to receive many messages 
                System.out.println("Reading message: " + m.getBody(String.class));
            } else {
                System.out.println("Message is not a TextMessage");
            }
        } catch (JMSException | JMSRuntimeException e) {
            System.err.println("JMSException in onMessage(): " + e.toString());
        } 
    }

    /* 
     * Retrieve the count value.
     */
    public long getCount() {
        return count.get();
    }
}
