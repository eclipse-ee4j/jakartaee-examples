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

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The EJB for the createQuery example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class CreateQueryEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get all the rows.
     * 
     * @return all the rows.
     */
    public List<CreateQueryRow> getRows() {
        return em.createQuery("SELECT object(r) FROM CreateQueryRow AS r", CreateQueryRow.class).getResultList();
    }
}
