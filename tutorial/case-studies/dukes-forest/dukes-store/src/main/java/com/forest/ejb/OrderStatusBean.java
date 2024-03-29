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
package com.forest.ejb;

import java.io.Serializable;

import com.forest.entity.OrderStatus;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 *
 * @author markito
 */
@Stateless
public class OrderStatusBean extends AbstractFacade<OrderStatus> implements Serializable {
    
    private static final long serialVersionUID = 5199196331433553237L;
    @PersistenceContext(unitName="forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderStatusBean() {
        super(OrderStatus.class);
    }

    public OrderStatus getStatusByName(String status) {
        Query createNamedQuery = getEntityManager().createNamedQuery("OrderStatus.findByStatus");

        //SELECT o FROM OrderStatus o WHERE o.status = :status
        createNamedQuery.setParameter("status", status);

        return (OrderStatus) createNamedQuery.getSingleResult();
}
    }
