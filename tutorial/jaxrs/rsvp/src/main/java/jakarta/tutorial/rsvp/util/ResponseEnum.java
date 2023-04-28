/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.rsvp.util;

public enum ResponseEnum {
    ATTENDING("Attending"),
    NOT_ATTENDING("Not attending"),
    MAYBE_ATTENDING("Maybe"),
    NOT_RESPONDED("No response yet");
    
    private final String label;
    
    private ResponseEnum(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
}
