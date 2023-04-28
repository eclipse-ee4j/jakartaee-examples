/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.taskcreator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/* The tasks just notify the JAX-RS web service in the EJB */
public class Task implements Runnable {

    private static final Logger log = Logger.getLogger("Task");
    private static final String WS_URL = 
            "http://localhost:8080/taskcreator/jaxrs/taskinfo";
    
    private final String name;
    private final String type;
    private final DateFormat dateFormat;
    private final Client client;
    private int counter;
    
    public Task(String n, String t) {
        name = n;
        type = t;
        counter = 1;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        client = ClientBuilder.newClient();
        /* For delayed tasks, send
        /* Send: 14:15:45 - DELAYED Task ABCDE submitted */
        if (type.compareTo("DELAYED") == 0)
            sendToWebService("submitted");
    }
    
    @Override
    public void run() {
        /* Send: 14:15:45 - TASKTYPE Task ABCDE started */
        if (type.compareTo("PERIODIC") == 0)
            sendToWebService("started run #" + counter);
        else
            sendToWebService("started");
        
        try {
            Thread.sleep(1500);
        } catch (Exception e) { }
        
        /* Send: 14:15:47 - TASKTYPE Task ABCDE finished */
        if (type.compareTo("PERIODIC") == 0)
            sendToWebService("finished run #" + (counter++));
        else
            sendToWebService("finished");
    }
    
    /* Send: 14:15:47 - TASKTYPE Task ABCDE [details] */
    private void sendToWebService(String details) {
        String time = dateFormat.format(Calendar.getInstance().getTime());
        String msg = time + " - "  + type + " Task " + name + " " + details;
        Response resp = client.target(WS_URL)
                              .request(MediaType.TEXT_PLAIN)
                              .post(Entity.html(msg));
    }
    
    public String getName() {
        return name;
    }

}
