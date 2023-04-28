/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.retailer;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Queue;

/**
 * The Retailer class orders a number of computers by sending a message to a
 * vendor. It then waits for the order to be confirmed.
 *
 * In this example, the Retailer places two orders, one for the quantity
 * specified on the command line and one for twice that number.
 *
 * This class does not use transactions.
 */
public class Retailer {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/AQueue")
    private static Queue vendorOrderQueue;
    @Resource(lookup = "jms/BQueue")
    private static Queue retailerConfirmQueue;
    static int quantity = 0;

    public static void main(String[] args) {
        MapMessage outMessage;
        JMSConsumer orderConfirmReceiver;
        MapMessage inMessage;

        if (args.length != 1) {
            System.out.println("Error: Program takes numerical argument.");
            System.exit(1);
        }

        quantity = Integer.parseInt(args[0]);
        System.out.println("Retailer: Quantity to be ordered is " + quantity);
        /*
         * Create non-transacted context and sender for
         * vendor order queue.
         * Create message to vendor, setting item and
         * quantity values.
         * Send message.
         * Create receiver for retailer confirmation queue.
         * Get message and report result.
         * Send an end-of-message-stream message so vendor
         * will stop processing orders.
         */
        try (JMSContext context = connectionFactory.createContext();) {
            outMessage = context.createMapMessage();
            outMessage.setString("Item", "Computer(s)");
            outMessage.setInt("Quantity", quantity);
            outMessage.setJMSReplyTo(retailerConfirmQueue);
            context.createProducer().send(vendorOrderQueue, outMessage);
            System.out.println(
                    "Retailer: Ordered " + quantity + " computer(s)");

            orderConfirmReceiver = context.createConsumer(
                    retailerConfirmQueue);
            inMessage = (MapMessage) orderConfirmReceiver.receive();

            if (inMessage.getBoolean("OrderAccepted") == true) {
                System.out.println("Retailer: Order filled");
            } else {
                System.out.println("Retailer: Order not filled");
            }

            System.out.println("Retailer: Placing another order");
            outMessage.setInt("Quantity", quantity * 2);
            context.createProducer().send(vendorOrderQueue, outMessage);
            System.out.println(
                    "Retailer: Ordered " + outMessage.getInt("Quantity")
                    + " computer(s)");
            inMessage = (MapMessage) orderConfirmReceiver.receive();

            if (inMessage.getBoolean("OrderAccepted") == true) {
                System.out.println("Retailer: Order filled");
            } else {
                System.out.println("Retailer: Order not filled");
            }

            /*
             * Send a non-text control message indicating end
             * of messages.
             */
            context.createProducer().send(vendorOrderQueue,
                    context.createMessage());
        } catch (JMSException e) {
            System.err.println(
                    "Retailer: Exception occurred: " + e.toString());
        }

    }
}
