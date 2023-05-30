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
package jakartaee.examples.faces.inputfile;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.Part;

/**
 * A request scoped bean for the h:inputFile example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "inputFileBean")
@RequestScoped
public class InputFileBean {
    
    /**
     * Stores the part.
     */
    private Part part;

    /**
     * Get the file (part).
     * 
     * @return the time.
     */
    public Part getFile() {
        return part;
    }
    
    /**
     * Set the file.
     * 
     * @param part the part.
     */
    public void setFile(Part part) {
        this.part = part;
    }
    
    /**
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        return "";
    }
}
