/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakartaee.tutorial.roster.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.tutorial.roster.util.IncorrectSportException;

@Entity
public class WinterLeague extends League implements Serializable {
    private static final long serialVersionUID = 8942582153559426625L;
    
    /** Creates a new instance of WinterLeague */
    public WinterLeague() {
    }
    
    public WinterLeague(String id, String name, String sport) throws 
            IncorrectSportException {
        this.id = id;
        this.name = name;
        if (sport.equalsIgnoreCase("hockey") ||
                sport.equalsIgnoreCase("skiing") ||
                sport.equalsIgnoreCase("snowboarding")) {
            this.sport = sport;
        } else {
            throw new IncorrectSportException("Sport is not a winter sport.");
        }
    }
    
}
