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
package jakartaee.examples.jpa.table;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Table.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("tableBean")
@RequestScoped
public class TableBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private TableEjb ejb;
    
    /**
     * Get the list of rows (instances of TableRow).
     * 
     * @return the list of rows.
     */
    public List<TableRow> getRows() {
        return ejb.getRows();
    }
}
