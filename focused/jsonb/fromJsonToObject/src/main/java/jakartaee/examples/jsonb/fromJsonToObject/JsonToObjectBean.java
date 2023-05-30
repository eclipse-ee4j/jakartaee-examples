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
package jakartaee.examples.jsonb.fromJsonToObject;

import java.util.List;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.JsonbBuilder;

/**
 * A request scoped bean for using with the JSON to Object example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "jsonToObjectBean")
@RequestScoped
public class JsonToObjectBean {
    
    /**
     * Stores the JSON.
     */
    private String json;
    
    /**
     * Stores the list.
     */
    private List list;

    /**
     * Get the JSON.
     * 
     * @return the JSON.
     */
    public String getJson() {
        return json;
    }
    
    /**
     * Get the list.
     * 
     * @return the list.
     */
    public List getList() {
        return list;
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
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        list = builder.build().fromJson("[" + json + "]", List.class);
        return "";
    }
}
