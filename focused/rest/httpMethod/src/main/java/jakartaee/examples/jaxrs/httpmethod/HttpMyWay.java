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
package jakartaee.examples.jaxrs.httpmethod;

import java.net.URI;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * The custom MYWAY HTTP method.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HttpMyWay extends HttpRequestBase {
    
    /**
     * Constructor.
     */
    public HttpMyWay() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param uri the URI.
     */
    public HttpMyWay(final URI uri) {
        super();
        setURI(uri);
    }
    
    /**
     * Constructor.
     * 
     * @param uri the URI.
     */
    public HttpMyWay(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    /**
     * Get the method.
     *
     * @return the method.
     */
    @Override
    public String getMethod() {
        return "MYWAY";
    }
}
