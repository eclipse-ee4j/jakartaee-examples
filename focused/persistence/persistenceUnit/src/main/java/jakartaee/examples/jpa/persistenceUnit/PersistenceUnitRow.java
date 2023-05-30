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

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * The JPA class for the @PersistenceUnit example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
public class PersistenceUnitRow implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Get the id.
     * 
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id.
     * 
     * @param id the id.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
