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
package jakartaee.examples.jpa.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Entity.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("entityExampleBean")
@RequestScoped
public class EntityExampleBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private EntityExampleEjb ejb;
    
    /**
     * Get the list of entities (instances of EntityExample).
     * 
     * @return the list of entities.
     */
    public List<EntityExample> getEntities() {
        return ejb.getEntities();
    }
}
