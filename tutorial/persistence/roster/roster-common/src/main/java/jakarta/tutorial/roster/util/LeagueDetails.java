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


public class LeagueDetails implements Serializable {
    private static final long serialVersionUID = 290368886584321980L;
    private String id;
    private String name;
    private String sport;

    public LeagueDetails(String id, String name, String sport) {

        this.id = id;
        this.name = name;
        this.sport = sport;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    @Override
    public String toString() {
        String s = id + " " + name + " " + sport;
        return s;
    }

}
