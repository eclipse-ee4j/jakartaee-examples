/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.clientsessionmdb.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * The MessageBean class is a message-driven bean. It implements the
 * jakarta.jms.MessageListener interface. It is defined as public (but not final
 * or abstract).
 */
/* At present, must define destination in MDB in order to use destinationLookup;
 * must use mappedName if destination is defined elsewhere 
 * (GlassFish issue 20715).
 */
@JMSDestinationDefinition(
        name = "java:module/jms/newsTopic",
        interfaceName = "jakarta.jms.Topic",
        destinationName = "PhysicalNewsTopic")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:module/jms/newsTopic"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "jakarta.jms.Topic"),
    @ActivationConfigProperty(propertyName = "messageSelector",
            propertyValue = "NewsType = 'Sports' OR NewsType = 'Opinion'"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability",
            propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId",
            propertyValue = "MyID"),
    @ActivationConfigProperty(propertyName = "subscriptionName",
            propertyValue = "MySub")
})
public class MessageBean implements MessageListener {

    static final Logger logger = Logger.getLogger("MessageBean");
    @Resource
    public MessageDrivenContext mdc;

    public MessageBean() {
    }

    /**
     * onMessage method, declared as public (but not final or static), with a
     * return type of void, and with one argument of type jakarta.jms.Message.
     *
     * Casts the incoming Message to a TextMessage and displays the text.
     *
     * @param inMessage the incoming message
     */
    @Override
    public void onMessage(Message inMessage) {

        try {
            if (inMessage instanceof TextMessage) {
                logger.log(Level.INFO,
                        "MESSAGE BEAN: Message received: {0}",
                        inMessage.getBody(String.class));
            } else {
                logger.log(Level.WARNING,
                        "Message of wrong type: {0}",
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE,
                    "MessageBean.onMessage: JMSException: {0}", e.toString());
            mdc.setRollbackOnly();
        }
    }
}
