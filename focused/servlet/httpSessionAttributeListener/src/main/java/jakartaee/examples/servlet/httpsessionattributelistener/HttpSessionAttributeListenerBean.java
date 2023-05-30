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

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

/**
 * The request scoped bean for the HttpSessionAttributeListener example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class HttpSessionAttributeListenerBean {

    /**
     * Stores the session.
     */
    @Inject
    private HttpSession session;

    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        session.setAttribute("attr0", "attr0-value");
        session.setAttribute("attr1", "attr1-value");
        session.removeAttribute("attr1");
        session.setAttribute("attr2", "attr2-value");
        session.setAttribute("attr2", "attr2-replaced-value");
        return "";
    }
}
