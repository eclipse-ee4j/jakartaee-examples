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

import java.util.Date;
import jakarta.json.bind.annotation.JsonbDateFormat;

/**
 * The model object for the JSON-B @JsonbDateFormat example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class JsonbDateFormatExample {

    /**
     * Stores the date.
     */
    @JsonbDateFormat(value = "MM/dd/yyyy")
    private Date date;

    /**
     * Get the date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date.
     * 
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
