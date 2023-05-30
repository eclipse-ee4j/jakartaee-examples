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
package jakartaee.examples.servlet.webservlet;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the Servlet API @WebServlet example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class WebServletServletIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Test the @WebServlet.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient
    @Test
    public void testWebServlet() throws Exception {
        TextPage page = webClient.getPage(baseUrl);
        String content = page.getContent();
        assertTrue(content.contains("And we called an @WebServlet servlet"));
    }
}
