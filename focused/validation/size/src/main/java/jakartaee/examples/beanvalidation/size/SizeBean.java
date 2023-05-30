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

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BeanValidation @Size example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class SizeBean {
    
    /**
     * Stores the size.
     */
    private SizeModel size = new SizeModel();

    /**
     * Get the size.
     * 
     * @return the text.
     */
    public SizeModel getSize() {
        return size;
    }
    
    /**
     * Set the size.
     * 
     * @param size the size.
     */
    public void setSize(SizeModel size) {
        this.size = size;
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
