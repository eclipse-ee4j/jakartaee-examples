/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.web.websocketbot.messages;

/* Represents an information message, like
 * an user entering or leaving the chat */
public class InfoMessage extends Message {
    
    private String info;
    
    public InfoMessage(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[InfoMessage] " + info;
    }
}
