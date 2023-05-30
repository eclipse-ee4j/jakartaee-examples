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
package jakartaee.examples.beanvalidation.notnull;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the BeanValidation @NotNull example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class NotNullBeanIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Test the @NotNull.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    @RunAsClient
    public void testNotNull() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl + "index.xhtml");
        String content = page.asXml();
        assertTrue(content.contains("This example demonstrates the use of @NotNull"));

        page = page.getElementByName("form:submitButton").click();
        assertTrue(page.asXml().contains("This cannot be null"));

        HtmlInput inputText = (HtmlInput) page.getElementByName("form:inputText");
        inputText.setValueAttribute("123");
        page = page.getElementByName("form:submitButton").click();
        assertFalse(page.asXml().contains("This cannot be null"));
    }
}
