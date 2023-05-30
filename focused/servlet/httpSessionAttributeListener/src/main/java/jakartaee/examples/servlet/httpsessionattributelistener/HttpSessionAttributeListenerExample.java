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
package jakartaee.examples.servlet.httpsessionattributelistener;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * An example HttpSessionAttributeListener.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example HttpSessionAttributeListener")
public class HttpSessionAttributeListenerExample implements HttpSessionAttributeListener {
    
    /**
     * Stores the servlet context.
     */
    @Inject
    ServletContext servletContext;

    /**
     * Handle the attribute added event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeAdded", event.getName());
        servletContext.setAttribute("attributeAddedValue", event.getValue());
    }

    /**
     * Handle the attribute removed event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeRemoved", event.getName());
    }

    /**
     * Handle the attribute replaced event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeReplaced", event.getName());
        servletContext.setAttribute("attributeReplacedValue", event.getValue());
    }
}
