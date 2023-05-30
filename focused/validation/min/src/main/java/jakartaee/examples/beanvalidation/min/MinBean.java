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

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BeanValidation @Min example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class MinBean {
    
    /**
     * Stores the min model.
     */
    private MinModel min = new MinModel();

    /**
     * Get the min model.
     * 
     * @return the min model.
     */
    public MinModel getMin() {
        return min;
    }
    
    /**
     * Set the min model.
     * 
     * @param min the min model.
     */
    public void setMin(MinModel min) {
        this.min = min;
    }
    
    /**
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        return "";
    }
}
