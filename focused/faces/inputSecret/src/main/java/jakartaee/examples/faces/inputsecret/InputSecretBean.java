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
package jakartaee.examples.faces.inputsecret;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the h:inputSecret example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class InputSecretBean {
    
    /**
     * Stores the part.
     */
    private String secret;

    /**
     * Get the secret.
     * 
     * @return the secret.
     */
    public String getSecret() {
        return secret;
    }
    
    /**
     * Set the secret.
     * 
     * @param secret the secret.
     */
    public void setSecret(String secret) {
        this.secret = secret;
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
