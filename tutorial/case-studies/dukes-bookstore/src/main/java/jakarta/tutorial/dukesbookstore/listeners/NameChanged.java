/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukesbookstore.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;

/**
 * <p>
 * Value change listener for the name entered on the
 * <code>bookcashier.xhtml</code> page.</p>
 */
public class NameChanged extends Object implements ValueChangeListener {

    private static final Logger logger = 
            Logger.getLogger("dukesbookstore.listeners.NameChanged");

    @Override
    public void processValueChange(ValueChangeEvent event)
            throws AbortProcessingException {
        logger.log(Level.INFO, "Entering NameChanged.processValueChange");
        if (null != event.getNewValue()) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("name", event.getNewValue());
        }
    }
}
