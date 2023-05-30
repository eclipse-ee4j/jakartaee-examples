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
package jakartaee.examples.faces.actionlistener;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the f:actionListener example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class ActionListenerIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Test the f:actionListener.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient
    @Test
    public void testActionListener() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl + "/index.xhtml");
        String content = page.asXml();
        assertTrue(content.contains("\"Hello World\""));

        HtmlSubmitInput submit = page.getHtmlElementById("form:submit");
        page = submit.click();
        content = page.asXml();
        assertTrue(content.contains("\"Hello World\""));
    }
}
