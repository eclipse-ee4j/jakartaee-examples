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
package jakartaee.examples.servlet.servletcontextlistener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * The ServletContextListener for the Servlet API ServletContextListener example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example ServletContextListener")
public class ServletContextListenerExample implements ServletContextListener {

    /**
     * Handles the servlet context initialized event.
     * 
     * @param event the servlet context initialized event. 
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute("contextInitializedCalled", "true");
    }
}
