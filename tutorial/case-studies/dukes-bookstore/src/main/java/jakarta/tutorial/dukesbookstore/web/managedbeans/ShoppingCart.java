/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.entity.Book;

/**
 * <p>Backing bean used by the
 * <code>/bookcatalog.xhtml</code>,
 * <code>/bookshowcart.xhtml</code>, and
 * <code>/bookcashier.xhtml</code> pages.</p>
 */
@Named("cart")
@SessionScoped
public class ShoppingCart extends AbstractBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger("dukesbookstore.web.managedbeans.ShoppingCart");
    private static final long serialVersionUID = -115105724952554868L;
    HashMap<String, ShoppingCartItem> items = null;
    int numberOfItems = 0;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public synchronized void add(String bookId, Book book) {
        if (items.containsKey(bookId)) {
            ShoppingCartItem scitem = (ShoppingCartItem) items.get(bookId);
            scitem.incrementQuantity();
        } else {
            ShoppingCartItem newItem = new ShoppingCartItem(book);
            items.put(bookId, newItem);
        }
    }

    public synchronized void remove(String bookId) {
        if (items.containsKey(bookId)) {
            ShoppingCartItem scitem = (ShoppingCartItem) items.get(bookId);
            scitem.decrementQuantity();

            if (scitem.getQuantity() <= 0) {
                items.remove(bookId);
            }

            numberOfItems--;
        }
    }

    public synchronized List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> results = new ArrayList<>();
        results.addAll(this.items.values());

        return results;
    }

    public synchronized int getNumberOfItems() {
        numberOfItems = 0;
        for (ShoppingCartItem item : getItems()) {
            numberOfItems += item.getQuantity();
        }

        return numberOfItems;
    }

    public synchronized double getTotal() {
        double amount = 0.0;
        for (ShoppingCartItem item : getItems()) {
            Book bookDetails = (Book) item.getItem();

            amount += (item.getQuantity() * bookDetails.getPrice());
        }

        return roundOff(amount);
    }

    private double roundOff(double x) {
        long val = Math.round(x * 100); // cents

        return val / 100.0;
    }

    /**
     * <p>Buy the items currently in the shopping cart.</p>
     * @return the navigation page
     */
    public String buy() {
        // "Cannot happen" sanity check
        if (getNumberOfItems() < 1) {
            message(null, "CartEmpty");

            return (null);
        } else {
            return ("bookcashier");
        }
    }

    public synchronized void clear() {
        logger.log(Level.INFO, "Clearing cart.");
        items.clear();
        numberOfItems = 0;
        message(null, "CartCleared");
    }
}
