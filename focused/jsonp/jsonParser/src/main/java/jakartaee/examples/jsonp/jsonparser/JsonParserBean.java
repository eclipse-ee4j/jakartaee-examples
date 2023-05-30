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
package jakartaee.examples.jsonp.jsonparser;

import java.io.StringReader;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

/**
 * A request scoped bean for the JsonParser example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonParserBean {

    /**
     * Stores the input.
     */
    private String input;

    /**
     * Stores the output.
     */
    private String output;

    /**
     * Get the input.
     *
     * @return the input.
     */
    public String getInput() {
        return input;
    }

    /**
     * Get the output.
     *
     * @return the output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Set the input.
     *
     * @param input the input.
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        JsonParser parser = Json.createParser(new StringReader(input));
        StringBuilder builder = new StringBuilder();
        while(parser.hasNext()) {
            Event event = parser.next();
            builder.append(event.toString()).append("\n");
        }
        output = builder.toString();
        return "";
    }
}
