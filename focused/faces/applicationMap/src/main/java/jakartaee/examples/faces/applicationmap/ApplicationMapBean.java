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
package jakartaee.examples.faces.applicationmap;

import java.io.Serializable;
import java.util.Map;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ApplicationMap;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * A request scoped bean for the injected ApplicationMap example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "applicationMapBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ApplicationMapBean implements Serializable {
    
    /**
     * Stores the application map.
     */
    @Inject
    @ApplicationMap
    private Map<String, Object> applicationMap;
    
    /**
     * Get the application map.
     * 
     * @return the application map.
     */
    public Map<String, Object> getApplicationMap() {
        return applicationMap;
    }
}
