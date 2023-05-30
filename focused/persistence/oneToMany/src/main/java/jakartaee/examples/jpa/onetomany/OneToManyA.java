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
import java.util.Set;
import static jakarta.persistence.CascadeType.ALL;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The JPA OneToManyA class for the OneToMany annotation example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_onetomany_a")
public class OneToManyA implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Stores the b's.
     */
    @OneToMany(cascade=ALL, mappedBy="a")
    private Set<OneToManyB> bs;

    /**
     * Get the b's.
     *
     * @return the b's.
     */
    public Set<OneToManyB> getBs() {
        return bs;
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
     * Set the b's.
     * 
     * @param bs the b's.
     */
    public void setBs(Set<OneToManyB> bs) {
        this.bs = bs;
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
