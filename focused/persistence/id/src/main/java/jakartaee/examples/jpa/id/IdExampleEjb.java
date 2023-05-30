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
package jakartaee.examples.jpa.id;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that gives access to the list of objects.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class IdExampleEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get the list of objects.
     * 
     * @return the list of object.
     */
    public List<IdExample> getObjects() {
        return em.createQuery("SELECT object(i) FROM IdExample AS i", IdExample.class).getResultList();
    }
}
