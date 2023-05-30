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
package jakartaee.examples.jpa.onetomany;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The JPA OneToManyB class for the OneToMany annotation example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_onetomany_b")
public class OneToManyB implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Get the a.
     *
     * @return the a.
     */
    @ManyToOne
    @JoinColumn(name = "a", nullable = false)
    private OneToManyA a;
    
    /**
     * Get the A.
     * 
     * @return the a.
     */
    public OneToManyA getA() {
        return a;
    }

    /**
     * Get the id.
     *
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the A.
     * 
     * @param a the A.
     */
    public void setA(OneToManyA a) {
        this.a = a ;
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
