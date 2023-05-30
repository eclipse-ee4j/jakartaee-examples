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
package jakartaee.examples.jaxrs.produces;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * The resource for the @Produces example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Path("produces")
public class ProducesResource {

    /**
     * Produces method.
     *
     * @return 'And we are returning an array of strings in JSON format'
     */
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String[] produces() {
        return new String[]{"And", "we", "are", "returning", "an", "array", "of", "strings", "in", "JSON", "format"};
    }
}
