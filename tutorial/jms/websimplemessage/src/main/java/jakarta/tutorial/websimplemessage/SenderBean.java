/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.websimplemessage;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Queue;

@JMSDestinationDefinition(
        name = "java:comp/jms/webappQueue",
        interfaceName = "jakarta.jms.Queue",
        destinationName = "PhysicalWebappQueue")
@Named
@RequestScoped
public class SenderBean {

    static final Logger logger = Logger.getLogger("SenderBean");
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:comp/jms/webappQueue")
    private Queue queue;
    private String messageText;

    /**
     * Creates a new instance of SenderBean
     */
    public SenderBean() {
    }

    /**
     * Get the value of messageText
     *
     * @return the value of messageText
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Set the value of messageText
     *
     * @param messageText new value of messageText
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /*
     * Within a try-with-resources block, create context.
     * Create message.
     * Create producer and send message.
     * Create FacesMessage to display on page.
     */
    public void sendMessage() {
        try {
            String text = "Message from producer: " + messageText;
            context.createProducer().send(queue, text);

            FacesMessage facesMessage =
                    new FacesMessage("Sent message: " + text);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (JMSRuntimeException t) {
            logger.log(Level.SEVERE,
                    "SenderBean.sendMessage: Exception: {0}",
                    t.toString());
        }
    }
}
