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
package jakartaee.examples.jaxrs.defaultvalue;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * The resource for the JAX-RS @DefaultValue example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Path("defaultValue")
public class DefaultValueResource {

    /**
     * defaultValue method.
     * 
     * @param queryParam the query parameter.
     * @return the value of the query parameter.
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultValue(@DefaultValue("defaultValue") @QueryParam("myparam") String queryParam) {
        return queryParam;
    }
}
