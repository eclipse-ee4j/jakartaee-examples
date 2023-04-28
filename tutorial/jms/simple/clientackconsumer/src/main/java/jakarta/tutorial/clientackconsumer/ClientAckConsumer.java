/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.clientackconsumer;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

public class ClientAckConsumer {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MyQueue")
    private static Queue queue;

    public static void main(String[] args) {
        JMSConsumer consumer;

        /*
         * Create client-acknowledge context for receiver.
         * Receive message and process it.
         * Acknowledge message.
         */
        try (JMSContext context =
                connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);) {
            System.out.println(
                    "Created client-acknowledge JMSContext");
            consumer = context.createConsumer(queue);

            while (true) {
                Message m = consumer.receive(1000);

                if (m != null) {
                    if (m instanceof TextMessage) {
                        // Comment out the following two lines to receive
                        // a large volume of messages
                        System.out.println(
                                "Reading message: " + m.getBody(String.class));
                        System.out.println(
                                "Acknowledging TextMessage");
                        context.acknowledge();
                    } else {
                        System.out.println(
                                "Acknowledging non-text control message");
                        context.acknowledge();
                        break;
                    }
                }
            }
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        }
    }
}
