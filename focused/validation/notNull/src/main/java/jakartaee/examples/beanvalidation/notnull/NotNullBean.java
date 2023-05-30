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

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BeanValidation @NotNull example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class NotNullBean {
    
    /**
     * Stores the not null model.
     */
    private NotNullModel notNull = new NotNullModel();

    /**
     * Get the not null model.
     * 
     * @return the not null model.
     */
    public NotNullModel getNotNull() {
        return notNull;
    }
    
    /**
     * Set the not null model.
     * 
     * @param notNull the not null model.
     */
    public void setNotNull(NotNullModel notNull) {
        this.notNull = notNull;
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
