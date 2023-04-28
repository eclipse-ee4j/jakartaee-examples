/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
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

import com.forest.events.OrderEvent;
import com.forest.qualifiers.New;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author markito
 */
@Named("EventDisptacherBean")
@Stateless
public class EventDispatcherBean {

     private static final Logger logger = Logger.getLogger(EventDispatcherBean.class.getCanonicalName());

    
    @Inject @New
    Event<OrderEvent> eventManager;

    @Asynchronous
    public void publish(OrderEvent event) {
        logger.log(Level.FINEST, "{0} Sending event from EJB", Thread.currentThread().getName());
        eventManager.fire(event);
    }
}
