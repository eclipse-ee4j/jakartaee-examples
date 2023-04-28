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
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.entity.Book;

/**
 * <p>Backing bean for the <code>/bookcatalog.xhtml</code> page.</p>
 */
@Named("catalog")
@SessionScoped
public class CatalogBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = -3594317405246398714L;
    private int totalBooks = 0;

    /**
     * @return the currently selected <code>Book</code> instance from the 
     * user request
     */
    protected Book book() {
        Book book;
        book = (Book) context().getExternalContext()
           .getRequestMap().get("book");

        return (book);
    }

    /**
     * <p>Add the selected item to our shopping cart.</p>
     * @return the navigation page
     */
    public String add() {
        Book book = book();
        cart.add(book.getBookId(), book);
        message(null, "ConfirmAdd", new Object[]{book.getTitle()});

        return ("bookcatalog");
    }

    /**
     * <p>Show the details page for the current book.</p>
     * @return the navigation page
     */
    public String details() {
        context().getExternalContext().getSessionMap().put("selected", book());

        return ("bookdetails");
    }

    public int getTotalBooks() {
        totalBooks = cart.getNumberOfItems();

        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getBookQuantity() {
        int bookQuantity = 0;
        Book book = book();

        if (book == null) {
            return bookQuantity;
        }

        List<ShoppingCartItem> results = cart.getItems();
        for (ShoppingCartItem item : results) {
            Book bd = (Book) item.getItem();

            if ((bd.getBookId()).equals(book.getBookId())) {
                bookQuantity = item.getQuantity();

                break;
            }
        }

        return bookQuantity;
    }
}
