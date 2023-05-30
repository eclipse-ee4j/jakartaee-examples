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
package jakartaee.examples.jpa.persistenceUnit;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

/**
 * A simple EJB that gives access to the list of PersistenceUnitRow instances.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class PersistenceUnitEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceUnit(unitName = "persistenceUnitPU")
    private EntityManagerFactory emf;
    
    /**
     * Get the list of PersistenceUnitRow instances.
     * 
     * <p>
     *  Note while this code works, it is not recommended that you manage the
     *  lifecycle of your EntityManager yourself. Use PersistenceContext in 
     *  your web application so you do not have to manage the EntityManager
     *  lifecycle yourself.
     * </p>
     * 
     * @return the list of PersistenceUnitRow instances.
     */
    public List<PersistenceUnitRow> getRows() {
        EntityManager em = emf.createEntityManager();
        List result = em.createQuery("SELECT object(e) FROM PersistenceUnitRow AS e", PersistenceUnitRow.class).getResultList();
        em.close();
        return result;
    }
}
