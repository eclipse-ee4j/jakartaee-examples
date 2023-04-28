/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.simplemessage.appclient;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

public class SimpleMessageClient {

    static final Logger logger = Logger.getLogger("SimpleMessageClient");
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MyQueue")
    private static Queue queue;

    public static void main(String[] args) {
        String text;
        final int NUM_MSGS = 3;

        try (JMSContext context = connectionFactory.createContext();) {
            
            for (int i = 0; i < NUM_MSGS; i++) {
                text = "This is message " + (i + 1);
                System.out.println("Sending message: " + text);
                context.createProducer().send(queue, text);
            }

            System.out.println("To see if the bean received the messages,");
            System.out.println(
                    " check <install_dir>/domains/domain1/logs/server.log.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred: {0}", e.toString());
        } 
        System.exit(0);
    } // main
} // class

