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
package jakartaee.examples.jsonb.jsonbdateformat;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

/**
 * The request scoped bean for the JSON-B @JsonbDateFormat example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonbDateFormatBean {
    
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
        JsonbConfig config = new JsonbConfig();
        config.withLocale(Locale.US);
        JsonbBuilder builder = JsonbBuilder.newBuilder().withConfig(config);
        JsonbDateFormatExample example = new JsonbDateFormatExample();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("MST"));
        calendar.set(2001, 8, 11);
        example.setDate(calendar.getTime());
        output = builder.build().toJson(example);
        return "";
    }
}
