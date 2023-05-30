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
package jakartaee.examples.jsonb.nullvalues;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

/**
 * A request scoped bean for using with the null values example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "nullValuesBean")
@RequestScoped
public class NullValuesBean {

    /**
     * Stores the JSON.
     */
    private String json;

    /**
     * Get the JSON.
     *
     * @return the JSON.
     */
    public String getJson() {
        return json;
    }

    /**
     * Set the JSON.
     *
     * @param json the json.
     */
    public void setJson(String json) {
        this.json = json;
    }
    
    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        JsonbConfig config = new JsonbConfig().withNullValues(Boolean.TRUE);
        JsonbBuilder builder = JsonbBuilder.newBuilder().withConfig(config);
        json = builder.build().toJson(new NullValues());
        return "";
    }
}
