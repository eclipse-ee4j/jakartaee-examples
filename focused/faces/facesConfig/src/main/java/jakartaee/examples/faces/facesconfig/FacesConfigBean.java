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
package jakartaee.examples.faces.facesconfig;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * A request scoped bean validating that JSF is in 2.3 mode.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "facesConfigBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class FacesConfigBean implements Serializable {
    
    /**
     * Stores the FacesContext.
     */
    @Inject
    private FacesContext facesContext;
    
    /**
     * Get the FacesContext.
     * 
     * @return the FacesContext.
     */
    public FacesContext getFacesContext() {
        return facesContext;
    }
}
