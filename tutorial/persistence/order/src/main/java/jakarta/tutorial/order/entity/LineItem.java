/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.order.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@IdClass(LineItemKey.class)
@Entity
@Table(name="PERSISTENCE_ORDER_LINEITEM")
@NamedQueries({
    @NamedQuery(
    name="findAllLineItems",
    query="SELECT l " +
          "FROM LineItem l"),
    @NamedQuery(
    name="findLineItemsByOrderId",
    query="SELECT l FROM LineItem l " +
          "WHERE l.customerOrder.orderId = :orderId " +
          "ORDER BY l.itemId"),
    @NamedQuery(
    name="findLineItemById",
    query="SELECT DISTINCT l FROM LineItem l " +
          "WHERE l.itemId = :itemId AND l.customerOrder.orderId = :orderId")
})
public class LineItem implements Serializable {
    private static final long serialVersionUID = 3229188813505619743L;
    private int itemId;
    private int quantity;
    private VendorPart vendorPart;
    private CustomerOrder customerOrder;
    
    public LineItem() {}
    
    public LineItem(CustomerOrder order, int quantity, VendorPart vendorPart) {
        this.customerOrder = order;
        this.itemId = order.getNextId();
        this.quantity = quantity;
        this.vendorPart = vendorPart;
    }

    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JoinColumn(name="VENDORPARTNUMBER")
    @ManyToOne
    public VendorPart getVendorPart() {
        return vendorPart;
    }

    public void setVendorPart(VendorPart vendorPart) {
        this.vendorPart = vendorPart;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="ORDERID")
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
