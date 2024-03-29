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
package jakarta.tutorial.traffic.rar.inbound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.endpoint.MessageEndpoint;
import jakarta.resource.spi.work.Work;

/* The RA runs this class to connect to the traffic information system
 * EIS and invoke methods on TrafficMdb */
public class TrafficServiceSubscriber implements Work {

    private static final Logger log = Logger.getLogger("TrafficServiceSocket");
    private MessageEndpoint mdb;
    private TrafficActivationSpec spec;
    private Socket socket;
    private volatile boolean listen;

    public TrafficServiceSubscriber(TrafficActivationSpec spec,
                                    MessageEndpoint mdb) {
        this.mdb = mdb;
        this.spec = spec;
        listen = true;
    }

    @Override
    public void run() {
        
        BufferedReader in;
        String jsonLine;
        String key;
        JsonParser parser;
        
        try {
            /* Connect to the traffic EIS */
            int port = Integer.parseInt(spec.getPort());
            log.info("[TrafficServiceSubscriber] Connecting...");
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            log.info("[TrafficServiceSubscriber] Connected");

            while (listen) {
                jsonLine = in.readLine();
                parser = Json.createParser(new StringReader(jsonLine));
                if (parser.hasNext() && parser.next() == Event.START_OBJECT &&
                    parser.hasNext() && parser.next() == Event.KEY_NAME) {
                    
                    key = parser.getString();
                    /* Does the MDB support this message? */
                    if (spec.getCommands().containsKey(key)) {
                        Method mdbMethod = spec.getCommands().get(key);
                        /* Invoke the method of the MDB */
                        callMdb(mdb, mdbMethod, jsonLine);
                    } else
                        log.info("[TrafficServerSubscriber] Unknown message");
                } else
                    log.info("[TrafficServiceSubscriber] Wrong message format");
                
            }
        } catch (IOException | ResourceException ex) {
            log.log(Level.INFO, "[TrafficServiceSubscriber] Error - {0}", ex.getMessage());
        }
    }
    
    /* Invoke a method from the MDB */
    private String callMdb(MessageEndpoint mdb, Method command, String... params) 
                           throws ResourceException {
        String resp;
        try {
            log.info("[TrafficServiceSubscriber] callMdb()");
            mdb.beforeDelivery(command);
            Object ret = command.invoke(mdb, (Object[]) params);
            resp = (String) ret;
        } catch (NoSuchMethodException | ResourceException | 
                 IllegalAccessException | IllegalArgumentException | 
                 InvocationTargetException ex) {
            log.info(String.format("Invocation error %s", ex.getMessage()));
            resp = "ERROR Invocation error - " + ex.getMessage();
        }
        mdb.afterDelivery();
        return resp;
    }

    @Override
    public void release() {
        log.info("[TrafficServiceSubscriber] release()");
        try {
            listen = false;
            socket.close();
        } catch (IOException ex) { }
    }
}
