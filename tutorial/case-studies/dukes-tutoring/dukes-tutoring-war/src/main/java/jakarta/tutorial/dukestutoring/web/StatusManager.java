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
package jakarta.tutorial.dukestutoring.web;

import java.util.Locale;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.tutorial.dukestutoring.util.StatusType;

/**
 *
 * @author ievans
 */
@Named
@RequestScoped
public class StatusManager {
    
    private final FacesContext ctx = FacesContext.getCurrentInstance();
    private final Locale locale;

    /** Creates a new instance of StatusManager */
    public StatusManager() {
        locale = ctx.getViewRoot().getLocale();
    }
    
    public String getLocalizedStatus(StatusType status) {
        return status.toString(locale);
    }

}
