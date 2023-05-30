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

import jakarta.ws.rs.Path;

/**
 * The JAX-RS resource for the @HttpMethod example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Path("httpmethod")
public class HttpMethodResource {

    /**
     * HttpMethod method.
     *
     * @return "And we accepted a MYWAY call"
     */
    @MYWAY
    public String httpMethod() {
        return "And we accepted a MYWAY call";
    }
}
