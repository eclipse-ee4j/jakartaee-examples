/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.mood;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 */
@WebListener()
public class SimpleServletListener implements ServletContextListener,
        ServletContextAttributeListener {

    static final Logger log =
            Logger.getLogger("mood.web.SimpleServletListener");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Context destroyed");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        log.log(Level.INFO, "Attribute {0} has been added, with value: {1}", 
                new Object[]{event.getName(), event.getValue()});
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        log.log(Level.INFO, "Attribute {0} has been removed", 
                event.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        log.log(Level.INFO, "Attribute {0} has been replaced, with value: {1}", 
                new Object[]{event.getName(), event.getValue()});
    }
}
