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
package jakarta.tutorial.roster.util;

import java.io.Serializable;

public class PlayerDetails implements Serializable {
    private static final long serialVersionUID = -5352446961599198526L;

    private String id;
    private String name;
    private String position;
    private double salary;

    public PlayerDetails() {
    }

    public PlayerDetails(String id, String name, String position, 
            double salary) {

        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        String s = id + " " + name + " " + position + " " + salary;
        return s;
    }

}
