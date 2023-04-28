/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton
@Startup
public class ConfigBean {

    @EJB
    private BookRequestBean request;

    @PostConstruct
    public void createData() {
        request.createBook("201", "Duke", "",
                "My Early Years: Growing Up on *7",
                30.75, false, 2005, "What a cool book.", 20);
        request.createBook("202", "Jeeves", "",
                "Web Servers for Fun and Profit", 40.75, true,
                2010, "What a cool book.", 20);
        request.createBook("203", "Masterson", "Webster",
                "Web Components for Web Developers",
                27.75, false, 2010, "What a cool book.", 20);
        request.createBook("205", "Novation", "Kevin",
                "From Oak to Java: The Revolution of a Language",
                10.75, true, 2008, "What a cool book.", 20);
        request.createBook("206", "Thrilled", "Ben",
                "The Green Project: Programming for Consumer Devices",
                30.00, true, 2008, "What a cool book.", 20);
        request.createBook("207", "Coding", "Happy",
                "Java Intermediate Bytecodes", 30.95, true,
                2010, "What a cool book.", 20);

    }
}
