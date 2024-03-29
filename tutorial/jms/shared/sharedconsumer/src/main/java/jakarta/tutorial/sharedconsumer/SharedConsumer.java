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
package jakarta.tutorial.sharedconsumer;

import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Topic;

/**
 * The SharedConsumer class consists only of a main method, which fetches one or
 * more messages from a topic using asynchronous message delivery. It uses the 
 * message listener TextListener. Run two instances of this program at the same
 * time, in conjunction with Producer. 
 */
public class SharedConsumer {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MyTopic")
    private static Topic topic;

    public static void main(String[] args) {
        JMSConsumer consumer;
        TextListener listener;
        InputStreamReader inputStreamReader;
        char answer = '\0';
        /*
         * In a try-with-resources block, create context.
         * Create shared consumer.
         * Receive all text messages from destination until
         * a non-text message is received indicating end of
         * message stream.
         * Report number of messages received.
         */
        try (JMSContext context = connectionFactory.createContext();) {
            consumer = context.createSharedConsumer(topic, "SubName");
            System.out.println("Waiting for messages on topic");
            
            listener = new TextListener();
            consumer.setMessageListener(listener);
            System.out.println(
                    "To end program, enter Q or q, " + "then <return>");
            inputStreamReader = new InputStreamReader(System.in);

            while (!((answer == 'q') || (answer == 'Q'))) {
                try {
                    answer = (char) inputStreamReader.read();
                } catch (IOException e) {
                    System.err.println("I/O exception: " + e.toString());
                }
            }
            System.out.println("Text messages received: " + listener.getCount());
        } catch (JMSRuntimeException e) {
            System.err.println("Exception occurred: " + e.toString());
            System.exit(1);
        }
        System.exit(0);
    }
}
