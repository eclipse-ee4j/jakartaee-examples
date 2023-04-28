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
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Queue;

@Named
@RequestScoped
public class ReceiverBean {

    static final Logger logger = Logger.getLogger("ReceiverBean");
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:comp/jms/webappQueue")
    private Queue queue;

    /**
     * Creates a new instance of ReceiverBean
     */
    public ReceiverBean() {
    }

    /*
     * Within a try-with-resources block, create context.
     * Create consumer, then receive message, using a timeout.
     * Create FacesMessage to display on page.
     */
    public void getMessage() {
        try {
            JMSConsumer receiver = context.createConsumer(queue);
            String text = receiver.receiveBody(String.class, 1000);

            if (text != null) {
                FacesMessage facesMessage =
                        new FacesMessage("Reading message: " + text);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else {
                FacesMessage facesMessage =
                        new FacesMessage("No message received after 1 second");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (JMSRuntimeException t) {
            logger.log(Level.SEVERE,
                    "ReceiverBean.getMessage: Exception: {0}",
                    t.toString());
        }
    }
}
