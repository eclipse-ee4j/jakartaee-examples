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
package jakarta.tutorial.web.websocketbot.messages;

/* Represents a join message for the chat */
public class JoinMessage extends Message {    
    private String name;
    
    public JoinMessage(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[JoinMessage] " + name;
    }
}
