/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.traffic.war;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.MessageDriven;
import jakarta.inject.Named;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

/* This bean asynchronously receives messages from a JMS
 * topic and calls a WebSocket server endpoint */
@Named
@MessageDriven(mappedName = "java:app/traffic-ejb/traffictopic")
public class WebMDB implements MessageListener {

    private static final Logger log = Logger.getLogger("WebSocketMDB");
    
    @Override
    public void onMessage(Message msg) {
        try {
            log.info("[WebMDB] onMessage()");
            String smsg = msg.getBody(String.class);
            log.log(Level.INFO, "[WebMDB] Received: {0}", smsg);
            TrafficEndpoint.sendAll(smsg);
        } catch (JMSException ex) {
            log.log(Level.INFO, "[WebMDB] Exception: {0}", ex.getMessage());
        }
    }

}
