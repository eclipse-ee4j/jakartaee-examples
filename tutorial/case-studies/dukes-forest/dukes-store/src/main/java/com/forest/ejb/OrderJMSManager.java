/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.forest.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.entity.CustomerOrder;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;
import jakarta.jms.QueueBrowser;

@JMSDestinationDefinition(
        name = "java:global/jms/OrderQueue",
        interfaceName = "jakarta.jms.Queue",
        destinationName = "PhysicalOrderQueue")
@Stateless
public class OrderJMSManager {

    private static final Logger logger = Logger.getLogger(OrderJMSManager.class.getCanonicalName());
    @Inject
    private JMSContext context;
    
    @Resource(mappedName = "java:global/jms/OrderQueue")
    private Queue queue;
    private QueueBrowser browser;

    public void sendMessage(CustomerOrder customerOrder) {
        ObjectMessage msgObj = context.createObjectMessage();

        try {
            msgObj.setObject(customerOrder);
            msgObj.setStringProperty("OrderID", String.valueOf(customerOrder.getId()));

            context.createProducer().send(queue, msgObj);
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMessage(int orderID) throws Exception {
        
        JMSConsumer consumer = context.createConsumer(queue, "OrderID='" + orderID + "'") ;
        
        CustomerOrder order = consumer.receiveBody(CustomerOrder.class, 1);
        
        if (order != null)
            logger.log(Level.INFO, "Order {0} removed from queue.", order.getId());
        else {
            logger.log(Level.SEVERE, "Order {0} was not removed from queue!", orderID); 
            throw new Exception("Order not removed from queue");
        }
        
    }
}
