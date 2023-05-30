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

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The JPA class for the @Table example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_column")
public class ColumnRow implements Serializable {
    
    /**
     * Stores the column.
     */
    @Column(name = "your_column_name")
    private String column;
    
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
     * Get the column.
     * 
     * @return the column.
     */
    public String getColumn() {
        return column;
    }
    
    /**
     * Set the column.
     * 
     * @param column the column.
     */
    public void setColumn(String column) {
        this.column = column;
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
