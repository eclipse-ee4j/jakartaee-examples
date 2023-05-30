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
package jakartaee.examples.faces.requestscoped;

import java.util.Date;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "requestScopedBean")
@RequestScoped
public class RequestScopedBean {

    /**
     * Get the time.
     * 
     * @return the time.
     */
    public String getTime() {
        return new Date().toString() + " - " + System.nanoTime();
    }
}
