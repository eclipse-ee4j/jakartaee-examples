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
package jakartaee.examples.jpa.column;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Column.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("columnBean")
@RequestScoped
public class ColumnBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private ColumnEjb ejb;
    
    /**
     * Get the list of rows (instances of ColumnRow).
     * 
     * @return the list of rows.
     */
    public List<ColumnRow> getRows() {
        return ejb.getRows();
    }
}
