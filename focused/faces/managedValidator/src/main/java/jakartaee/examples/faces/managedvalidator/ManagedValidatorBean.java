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
package jakartaee.examples.faces.managedvalidator;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;

/**
 * The bean for the CDI managed validator example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ManagedValidatorBean {
    
    /**
     * Stores the value.
     */
    private Object value = "A value";

    /**
     * Get the value.
     * 
     * @return the value.
     */
    public Object getValue() {
        return value;
    }
    
    /**
     * Set the value.
     * 
     * @param value the value.
     */
    public void setValue(Object value) {
        this.value = value;
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
