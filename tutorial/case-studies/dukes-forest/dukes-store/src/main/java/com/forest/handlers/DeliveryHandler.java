/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.forest.handlers;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.ejb.OrderBean;
import com.forest.ejb.OrderJMSManager;
import com.forest.entity.CustomerOrder;
import com.forest.events.OrderEvent;
import com.forest.qualifiers.Paid;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Observes;

/**
 *
 * @author markito
 */
@Stateless
public class DeliveryHandler implements IOrderHandler, Serializable {

    private static final Logger logger = Logger.getLogger(DeliveryHandler.class.getCanonicalName());
    private static final long serialVersionUID = 4346750267714932851L;
    
    @EJB 
    OrderBean orderBean;
    @EJB
    OrderJMSManager orderPublisher;
    
    @Override
    @Asynchronous
    public void onNewOrder(@Observes @Paid OrderEvent event) {
        
        logger.log(Level.FINEST, "{0} Event being processed by DeliveryHandler", Thread.currentThread().getName());
       
        try {           
            logger.log(Level.INFO, "Order #{0} has been paid in the amount of {1}. Order is now ready for delivery!", new Object[]{event.getOrderID(), event.getAmount()});
                                    
            orderBean.setOrderStatus(event.getOrderID(), String.valueOf(OrderBean.Status.READY_TO_SHIP.getStatus()));
            CustomerOrder order = orderBean.getOrderById(event.getOrderID());
            if (order != null) {
                orderPublisher.sendMessage(order);
               
            } else {
                throw new Exception("The order does not exist");
            }
        } catch (Exception jex) {
            logger.log(Level.SEVERE, null, jex);
        }
    }
}
