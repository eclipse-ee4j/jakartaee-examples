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
package jakartaee.examples.jpa.converter;

/**
 * The attribute used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class ConverterAttribute {
    
    /**
     * Stores the long.
     */
    private Long value;
    
    /**
     * Constructor.
     */
    public ConverterAttribute() {
    }
    
    /**
     * Constructor.
     * 
     * @param value the value.
     */
    public ConverterAttribute(Long value) {
        this.value = value;
    }
    
    /**
     * Get the value.
     * 
     * @return the value.
     */
    public Long getValue() {
        return value;
    }
    
    /**
     * Set the value.
     * 
     * @param value the value.
     */
    public void setValue(Long value) {
        this.value = value;
    }
}
