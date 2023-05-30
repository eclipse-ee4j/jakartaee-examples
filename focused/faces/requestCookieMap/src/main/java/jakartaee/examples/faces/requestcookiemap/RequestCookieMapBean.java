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
package jakartaee.examples.faces.requestcookiemap;

import java.io.Serializable;
import java.util.Map;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.annotation.RequestCookieMap;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * A request scoped bean injecting the request cookie map.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "requestCookieMapBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class RequestCookieMapBean implements Serializable {
    
    /**
     * Stores the request cookie map.
     */
    @Inject
    @RequestCookieMap
    private Map<String, Object> requestCookieMap;
    
    /**
     * Get the request cookie map.
     * 
     * @return the request cookie map.
     */
    public Map<String, Object> getRequestCookieMap() {
        return requestCookieMap;
    }
}
