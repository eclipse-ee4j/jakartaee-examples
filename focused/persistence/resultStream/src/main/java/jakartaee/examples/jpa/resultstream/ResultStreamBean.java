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
package jakartaee.examples.jpa.resultstream;

import java.io.Serializable;
import java.util.stream.Collectors;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean for the resultStream example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("resultStreamBean")
@RequestScoped
public class ResultStreamBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private ResultStreamEjb ejb;
    
    /**
     * Get all the rows.
     * 
     * @return all the rows.
     */
    public String getOutput() {
        String result = ejb.getRows().map(r->r.getId().toString()).collect(Collectors.joining(","));
        return result;
    }
}
