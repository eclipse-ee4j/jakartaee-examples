/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.forest.shipment.ejb;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.entity.CustomerOrder;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import jakarta.jms.QueueBrowser;

@Stateless
public class OrderBrowser {

    private static final Logger logger = Logger.getLogger(OrderBrowser.class.getCanonicalName());
    @Inject
    private JMSContext context;
    @Resource(mappedName = "java:global/jms/OrderQueue")
    private Queue queue;
    private QueueBrowser browser;

    public Map<String, CustomerOrder> getOrders() {
        browser = context.createBrowser(queue);
        Enumeration msgs;
        try {
            msgs = browser.getEnumeration();

            if (!msgs.hasMoreElements()) {
                logger.log(Level.INFO, "No messages on the queue!");
            } else {

                Map<String, CustomerOrder> result = new LinkedHashMap<>();
                while (msgs.hasMoreElements()) {
                    Message msg = (Message) msgs.nextElement();

                    logger.log(Level.INFO, "Message ID: {0}", msg.getJMSMessageID());
                    CustomerOrder order = msg.getBody(CustomerOrder.class);
                    result.put(msg.getJMSMessageID(), order);
                }
                return result;
            }
        } catch (JMSException ex) {
            Logger.getLogger(OrderBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public CustomerOrder processOrder(String OrderMessageID) {

        logger.log(Level.INFO, "Processing Order {0}", OrderMessageID);
        JMSConsumer consumer = context.createConsumer(queue, "JMSMessageID='" + OrderMessageID + "'");

        CustomerOrder order = consumer.receiveBody(CustomerOrder.class, 1);
        return order;
    }
}
