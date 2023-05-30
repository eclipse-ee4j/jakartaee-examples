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
package jakartaee.examples.servlet.httpsessionlistener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * An example HttpSessionListener.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example HttpSessionListener")
public class HttpSessionListenerExample implements HttpSessionListener {

    /**
     * Handles the session created event.
     * 
     * @param event the HTTP session event. 
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session " + event.getSession().getId() + " was created");
    }

    /**
     * Handles the session destroyed event.
     * 
     * @param event the HTTP session event. 
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session " + event.getSession().getId() + " was destroyed");
    }
}
