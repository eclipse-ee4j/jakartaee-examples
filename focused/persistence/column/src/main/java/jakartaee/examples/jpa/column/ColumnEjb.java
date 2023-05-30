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

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that gives access to the list of rows.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class ColumnEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get the list of rows.
     * 
     * @return the list of rows.
     */
    public List<ColumnRow> getRows() {
        return em.createQuery("SELECT object(r) FROM ColumnRow AS r", ColumnRow.class).getResultList();
    }
}
