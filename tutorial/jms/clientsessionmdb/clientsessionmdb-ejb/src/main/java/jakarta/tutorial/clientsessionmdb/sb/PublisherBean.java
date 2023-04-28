/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.clientsessionmdb.sb;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.Remote;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;

/**
 * Bean class for Publisher enterprise bean. Defines publishNews business method
 * as well as required methods for a stateless session bean.
 */
@Stateless
@Remote({
    PublisherRemote.class
})
public class PublisherBean implements PublisherRemote {

    @Resource
    private SessionContext sc;
    @Resource(lookup = "java:module/jms/newsTopic")
    private Topic topic;
    @Inject
    private JMSContext context;
    static final String[] messageTypes = {
        "Nation/World", "Metro/Region", "Business", "Sports", "Living/Arts",
        "Opinion"
    };
    static final Logger logger = Logger.getLogger("PublisherBean");

    public PublisherBean() {
    }

    /**
     * Chooses a message type by using the random number generator found in
     * java.util. Called by publishNews().
     *
     * @return the String representing the message type
     */
    private String chooseType() {
        int whichMsg;
        Random rgen = new Random();

        whichMsg = rgen.nextInt(messageTypes.length);

        return messageTypes[whichMsg];
    }

    /**
     * Creates producer and message. Sends messages after setting their NewsType
     * property and using the property value as the message text. Messages are
     * received by MessageBean, a message-driven bean that uses a message
     * selector to retrieve messages whose NewsType property has certain values.
     */
    @Override
    public void publishNews() {
        TextMessage message;
        int numMsgs = messageTypes.length * 3;
        String messageType;

        try {
            message = context.createTextMessage();

            for (int i = 0; i < numMsgs; i++) {
                messageType = chooseType();
                message.setStringProperty("NewsType", messageType);
                message.setText("Item " + i + ": " + messageType);
                logger.log(Level.INFO,
                        "PUBLISHER: Setting message text to: {0}",
                        message.getText());
                context.createProducer().send(topic, message);
            }
        } catch (JMSException t) {
            logger.log(Level.SEVERE,
                    "PublisherBean.publishNews: Exception: {0}", t.toString());
            sc.setRollbackOnly();
        }
    }
}
