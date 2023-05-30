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
package jakartaee.examples.faces.outputlabel;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the h:outputLabel example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class OutputLabelBean {
    
    /**
     * Stores the output text.
     */
    private String outputLabel = "'Hello World'";

    /**
     * Get the output label.
     * 
     * @return the output label.
     */
    public String getOutputLabel() {
        return outputLabel;
    }
    
    /**
     * Set the output label.
     * 
     * @param outputLabel the output label.
     */
    public void setOutputLabel(String outputLabel) {
        this.outputLabel = outputLabel;
    }
}
