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
package jakartaee.examples.jpa.onetoone;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean for the OneToOne annotation example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class OneToOneBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private OneToOneEjb ejb;
    
    /**
     * Get the OneToOneA.
     * 
     * @return the OneToOneA.
     */
    public OneToOneA getOneToOne() {
        return ejb.getOneToOneA();
    }
}
