/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
