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
package jakartaee.examples.faces.externalcontext;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * A request scoped bean injecting the ExternalContext.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "externalContextBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ExternalContextBean implements Serializable {
    
    /**
     * Stores the ExternalContext.
     */
    @Inject
    private ExternalContext externalContext;
    
    /**
     * Get the ExternalContext.
     * 
     * @return the ExternalContext.
     */
    public ExternalContext getExternalContext() {
        return externalContext;
    }
}
