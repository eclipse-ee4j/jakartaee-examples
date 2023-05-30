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
package jakartaee.examples.faces.managedproperty;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * A bean using a managed property.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "managedPropertyBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ManagedPropertyBean implements Serializable {
    
    /**
     * Stores the context path using a managed property.
     */
    @Inject
    @ManagedProperty(value = "#{externalContext.requestContextPath}")
    private String contextPath;
    
    /**
     * Get the context path.
     * 
     * @return the context path.
     */
    public String getContextPath() {
        return contextPath;
    }
}
