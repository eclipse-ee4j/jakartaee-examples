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
package jakartaee.examples.beanvalidation.min;

import jakarta.validation.constraints.Min;

/**
 * A model class for the BeanValidation @Min example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class MinModel {
    
    /**
     * Stores the value.
     */
    @Min(value = 4)
    private Integer value;
    
    /**
     * Get the value.
     * 
     * @return the value.
     */
    public Integer getValue() {
        return value;
    }
    
    /**
     * Set the value.
     * 
     * @param value the value.
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}
