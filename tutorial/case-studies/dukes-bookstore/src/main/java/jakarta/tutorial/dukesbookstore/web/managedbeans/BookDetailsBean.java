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

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.entity.Book;

/**
 * <p>Backing bean for the <code>/bookdetails.xhtml</code> page.</p>
 */
@Named("details")
@SessionScoped
public class BookDetailsBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 2209748452115843974L;

    /**
     * <p>Add the displayed item to our shopping cart.</p>
     * @return the navigation page
     */
    public String add() {
        Book book = (Book) context().getExternalContext()
                .getSessionMap().get("selected");
        cart.add(book.getBookId(), book);
        message(null, "ConfirmAdd", new Object[]{book.getTitle()});

        return ("bookcatalog");
    }
}
