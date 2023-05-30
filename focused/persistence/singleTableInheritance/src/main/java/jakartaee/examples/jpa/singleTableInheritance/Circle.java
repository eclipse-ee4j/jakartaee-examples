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
package jakartaee.examples.jpa.singleTableInheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */

@Entity
@DiscriminatorValue("CIRCLE")
public class Circle extends Shape {

    private Float radious;

    public Circle() {
    }    
    
    public Circle(Float radious) {
        this.radious = radious;
    }    
    
    public Float getRadious() {
        return radious;
    }

    public void setRadious(Float radious) {
        this.radious = radious;
    }
        
}
