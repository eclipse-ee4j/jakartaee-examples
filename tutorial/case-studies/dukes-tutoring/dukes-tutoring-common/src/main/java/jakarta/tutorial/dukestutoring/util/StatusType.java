/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukestutoring.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author ian
 */
public enum StatusType {
    IN,
    OUT,
    PARK;

    public String toString(Locale locale) {
        ResourceBundle res = ResourceBundle.getBundle("jakarta.tutorial.dukestutoring.util.StatusMessages", locale);
        return res.getString(name() + ".string");
    }
    
    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle("jakarta.tutorial.dukestutoring.util.StatusMessages", locale);
        return res.getString(name() + ".string");
    }
}
