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
package jakartaee.examples.faces.commandscript;

import java.io.Serializable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * A request scoped bean for the h:commandScript example.
 * ˆ
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "commandScriptBean")
@RequestScoped
public class CommandScriptBean implements Serializable {
    
    /**
     * Stores the output.
     */
    private String output;

    /**
     * Get the output.
     * 
     * @return the output.
     */
    public String getOutput() {
        return output;
    }
    
    /**
     * Process the call.
     * 
     * @return null
     */
    public Object process() {
        output = "And we processed the call";
        return null;
    }
}
