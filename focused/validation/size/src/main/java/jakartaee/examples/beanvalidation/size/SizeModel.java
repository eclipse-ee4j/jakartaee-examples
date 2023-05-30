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
package jakartaee.examples.beanvalidation.size;

import jakarta.validation.constraints.Size;

/**
 * The model class for the BeanValidation @Size example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class SizeModel {
    
    /**
     * Stores the string.
     */
    @Size(min = 4)
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
