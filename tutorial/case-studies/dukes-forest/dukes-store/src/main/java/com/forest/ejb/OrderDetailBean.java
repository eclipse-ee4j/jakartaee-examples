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

import java.util.List;

import com.forest.entity.OrderDetail;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author markito
 */
@Stateless
public class OrderDetailBean extends AbstractFacade<OrderDetail> {
    @PersistenceContext(unitName="forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailBean() {
        super(OrderDetail.class);
    }

    /**
     * Example of usage of NamedQuery
     * @param orderId
     * @return 
     */
    public List<OrderDetail> findOrderDetailByOrder(int orderId) {
        List<OrderDetail> details = getEntityManager().createNamedQuery("OrderDetail.findByOrderId").setParameter("orderId", orderId).getResultList();
        
        return details;
    }
}
