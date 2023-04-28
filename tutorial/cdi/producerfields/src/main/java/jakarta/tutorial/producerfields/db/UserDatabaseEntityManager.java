/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.producerfields.db;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
public class UserDatabaseEntityManager {

    // declare a producer field
    @Produces
    @UserDatabase 
    @PersistenceContext
    private EntityManager em;

    // use methods to create and dispose of a producer field
 /* @PersistenceContext
    private EntityManager em;

    @Produces
    @UserDatabase
    public EntityManager create() {
        return em;
    }

    public void close(@Disposes @UserDatabase EntityManager em) {
        em.close();
    } */
}
