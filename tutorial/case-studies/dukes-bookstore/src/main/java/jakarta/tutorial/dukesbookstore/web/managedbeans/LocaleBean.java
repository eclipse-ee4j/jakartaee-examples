/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 * <p>Managed bean that retrieves current locale, used on each page.</p>
 */
@Named
@SessionScoped
public class LocaleBean extends AbstractBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger("dukesbookstore.web.managedbeans.LocaleBean");
    private static final long serialVersionUID = -2181710426297811604L;
    private Locale locale =
            context().getViewRoot().getLocale();

    public LocaleBean() {
    }

    public Locale getLocale() {
        logger.log(Level.INFO, "Entering LocaleBean.getLocale");
        return locale;
    }

    public void setLocale(Locale locale) {
        logger.log(Level.INFO, "Entering LocaleBean.setLocale");
        this.locale = locale;
    }

    public String getLanguage() {
        Locale newlocale = null;
        logger.log(Level.INFO, "Entering LocaleBean.getLanguage");
        String lang = locale.getLanguage();
        Map map = context().getExternalContext().getSessionMap();
        if (map.containsKey("locale")) {
            newlocale = (Locale) map.get("locale");
        }
        if (!(newlocale == null)) {
            String newlang = newlocale.getLanguage();
            if (!newlang.equals(lang)) {
                return newlang;
            }
        }
        return lang;
    }

    public void setLanguage(String language) {
        logger.log(Level.INFO, "Entering LocaleBean.setLanguage");
        locale = new Locale(language);
        context().getViewRoot().setLocale(locale);
    }
}
