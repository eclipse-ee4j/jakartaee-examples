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
package jakartaee.examples.beanvalidation.notnull;

import jakarta.validation.constraints.NotNull;

/**
 * The model for the BeanValidation @NotNull example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class NotNullModel {
    
    /**
     * Stores the string.
     */
    @NotNull(message = "This cannot be null")
    private String string;
    
    /**
     * Get the string.
     * 
     * @return the string.
     */
    public String getString() {
        return string;
    }
    
    /**
     * Set the string.
     * 
     * @param string the string.
     */
    public void setString(String string) {
        this.string = string;
    }
}
