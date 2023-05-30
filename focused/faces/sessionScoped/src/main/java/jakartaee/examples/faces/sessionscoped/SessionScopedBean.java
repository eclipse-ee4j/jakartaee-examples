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
package jakartaee.examples.faces.sessionscoped;

import java.io.Serializable;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

/**
 * A session scoped bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "sessionScopedBean")
@SessionScoped
public class SessionScopedBean implements Serializable {
    
    /**
     * Stores the time.
     */
    private String time;

    /**
     * Initialize the bean.
     */
    @PostConstruct
    public void initialize() {
        time = new Date().toString() + " - " + System.nanoTime();
    }
    
    /**
     * Get the time.
     * 
     * @return the time.
     */
    public String getTime() {
        return time;
    }
}
