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
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package jakarta.tutorial.dukestutoring.web;

import java.io.Serializable;
import java.util.Locale;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 *
 * @author ievans
 */
@Named(value = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {
    private static final long serialVersionUID = 6469476733525879600L;
    private Locale locale = FacesContext.getCurrentInstance()
            .getViewRoot()
            .getLocale();
    
    /**
     * Creates a new instance of LocaleBean
     */
    public LocaleBean() {
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
