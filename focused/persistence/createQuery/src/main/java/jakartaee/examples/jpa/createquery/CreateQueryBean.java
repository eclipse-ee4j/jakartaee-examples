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
package jakartaee.examples.jpa.createquery;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean for the createQuery example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("createQueryBean")
@RequestScoped
public class CreateQueryBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private CreateQueryEjb ejb;
    
    /**
     * Get all the rows.
     * 
     * @return all the rows.
     */
    public List<CreateQueryRow> getRows() {
        return ejb.getRows();
    }
}
