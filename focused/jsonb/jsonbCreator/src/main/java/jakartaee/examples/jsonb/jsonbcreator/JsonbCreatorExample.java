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
package jakartaee.examples.jsonb.jsonbcreator;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 * The model object for the JSON-B @JsonbCreator example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class JsonbCreatorExample {

    /**
     * Stores the integer.
     */
    private int integer;

    /**
     * Constructor.
     * 
     * @param integerString the integer in string format.
     */
    @JsonbCreator
    public JsonbCreatorExample(@JsonbProperty("integer") String integerString) {
        integer = Integer.valueOf(integerString);
    }
    
    /**
     * Get the date.
     *
     * @return the date.
     */
    public int getInt() {
        return integer;
    }
}
