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
package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.entity.Book;

/**
 * <p>Backing bean for the <code>/bookshowcart.xhtml</code> page.</p>
 */
@Named("showcart")
@SessionScoped
public class ShowCartBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 2287968973374093614L;
    private boolean cartChanged = false;

    public boolean isCartChanged() {
        return cartChanged;
    }

    public void setCartChanged(boolean cartChanged) {
        this.cartChanged = cartChanged;
    }

    /**
     * @return the <code>ShoppingCartItem</code> instance from the user request
     */
    protected ShoppingCartItem item() {
        ShoppingCartItem item = (ShoppingCartItem) context()
                .getExternalContext().getRequestMap().get("item");

        return (item);
    }

    /**
     * <p>Show the details page for the current book.</p>
     * @return the navigation page
     */
    public String details() {
        context().getExternalContext().getSessionMap()
                .put("selected", item().getItem());

        return ("bookdetails");
    }

    /**
     * <p>Remove the item from the shopping cart.</p>
     * @return null string
     */
    public String remove() {
        Book book = (Book) item().getItem();
        cart.remove(book.getBookId());
        setCartChanged(true);
        message(null, "ConfirmRemove", new Object[]{book.getTitle()});

        return (null);
    }

    /**
     * <p>Report the change to the shopping cart, based on the values entered in
     * the "Quantity" column.</p>
     * @return null string
     */
    public String update() {
        String changed = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("changed");
        if ((changed != null) && changed.equals("true")) {
            setCartChanged(true);
        } else {
            setCartChanged(false);
        }
        if (isCartChanged()) {
            message(null, "QuantitiesUpdated");
        } else {
            message(null, "QuantitiesNotUpdated");
        }
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("changed", "false");
        return (null);
    }
}
