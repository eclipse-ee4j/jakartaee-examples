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

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/* This endpoint forwards to web clients the JSON messages 
 * received by the WebMDB bean from the JMS topic */
@ServerEndpoint("/wstraffic")
public class TrafficEndpoint {
    
    /* Queue for all open WebSocket sessions */
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    
    private static final Logger log = Logger.getLogger("TrafficEndpoint");
    
    /* Called by WebMDB when it receives messages from the JMS topic */
    public static synchronized void sendAll(String msg) {
        log.info("[TrafficEndpoint] sendAll()");
        try {
            /* Send messages from the JMS queue to all sessions */
            for (Session session : queue) {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(msg);
                    log.log(Level.INFO, "[TrafficEndpoint] Sent: {0}", msg);
                }
            }
        } catch (IOException e) {
            log.log(Level.INFO, "[TrafficEndpoint] Exception: {0}", e.getMessage());
        }
    }
    
    /* Add and remove sesions from the queue */
    @OnOpen
    public void openConnection(Session session) {
        log.info("[TrafficEndpoint] openConnection()");
        queue.add(session);
    }
    
    @OnClose
    public void closedConnection(Session session) {
        log.info("[TrafficEndpoint] closedConnection()");
        queue.remove(session);
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        log.info("[TrafficEndpoint] error()");
    }
}
