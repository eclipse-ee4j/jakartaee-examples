/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.order.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name="PERSISTENCE_ORDER_CUSTOMERORDER")
@NamedQuery(
    name="findAllOrders",
    query="SELECT co FROM CustomerOrder co " +
          "ORDER BY co.orderId"
)
public class CustomerOrder implements java.io.Serializable {
    private static final long serialVersionUID = 6582105865012174694L;
    private Integer orderId;
    private char status;
    private Date lastUpdate;
    private int discount;
    private String shipmentInfo;
    private Collection<LineItem> lineItems;
    
    public CustomerOrder() {
        this.lastUpdate = new Date();
        this.lineItems = new ArrayList<>();
    }
    
    public CustomerOrder(Integer orderId, char status, int discount, 
            String shipmentInfo) {
        this.orderId = orderId;
        this.status = status;
        this.discount = discount;
        this.shipmentInfo = shipmentInfo;
        this.lastUpdate = new Date();
        this.lineItems = new ArrayList<>();
    }
    
    @Id
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public char getStatus() {
        return status;
    }
    
    public void setStatus(char status) {
        this.status = status;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public int getDiscount() {
        return discount;
    }
    
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public String getShipmentInfo() {
        return shipmentInfo;
    }
    
    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="customerOrder")
    public Collection<LineItem> getLineItems() {
        return lineItems;
    }
    
    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public double calculateAmmount() {
        double ammount = 0;
        Collection<LineItem> items = getLineItems();
        for (LineItem item : items) {
            VendorPart part = item.getVendorPart();
            ammount += part.getPrice() * item.getQuantity();
        }
        return (ammount * (100 - getDiscount()))/100;
    }

    public void addLineItem(LineItem lineItem) {
        this.getLineItems().add(lineItem);
    }
    
    @Transient
    public int getNextId() {
        return this.lineItems.size() + 1;
    }
}
