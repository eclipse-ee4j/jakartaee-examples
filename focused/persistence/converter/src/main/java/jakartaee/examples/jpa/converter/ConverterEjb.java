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

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The EJB used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class ConverterEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get the converter example.
     * 
     * @return the converter example.
     */
    public ConverterExample getConverterExample() {
        List<ConverterExample> result = em.createQuery(
                "SELECT object(r) FROM ConverterExample AS r", ConverterExample.class).getResultList();
        if (result.isEmpty()) {
            ConverterExample row = new ConverterExample();
            row.setId(1L);
            row.setColumn(new ConverterAttribute(1234L));
            em.persist(row);
            result = em.createQuery(
                "SELECT object(r) FROM ConverterExample AS r", ConverterExample.class).getResultList();
        }
        return result.get(0);
    }
}
