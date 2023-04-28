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

/* Represents a chat message */
public class ChatMessage extends Message {
    private String name;
    private String target;
    private String message;
    
    public ChatMessage(String name, String target, String message) {
        this.name = name;
        this.target = target;
        this.message = message;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTarget() {
        return target;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[ChatMessage] " + name + "-" + target + "-" + message;
    }
}
