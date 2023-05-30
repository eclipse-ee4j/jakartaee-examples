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
package jakartaee.examples.beanvalidation.nullannotation;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BenValidation @Null example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class NullBean {
    
    /**
     * Stores the null model.
     */
    private NullModel nullModel = new NullModel();

    /**
     * Get the null model.
     * 
     * @return the null model.
     */
    public NullModel getNullModel() {
        return nullModel;
    }
    
    /**
     * Set the null model.
     * 
     * @param nullModel the null model.
     */
    public void setNullModel(NullModel nullModel) {
        this.nullModel = nullModel;
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
