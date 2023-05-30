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
package jakartaee.examples.jsonp.jsongenerator;

import java.io.StringWriter;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

/**
 * A request scoped bean for the JsonGenerator example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonGeneratorBean {


    /**
     * Stores the output.
     */
    private String output;

    /**
     * Get the output.
     *
     * @return the output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        generator.write("property", "propertyValue");
        generator.writeEnd();
        generator.flush();
        output = writer.toString();
        return "";
    }
}
