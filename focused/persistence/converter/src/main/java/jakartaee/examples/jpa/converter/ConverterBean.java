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
package jakartaee.examples.jpa.converter;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class ConverterBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private ConverterEjb ejb;
    
    /**
     * Get the output.
     * 
     * @return the output.
     */
    public ConverterExample getOutput() {
        return ejb.getConverterExample();
    }
}
