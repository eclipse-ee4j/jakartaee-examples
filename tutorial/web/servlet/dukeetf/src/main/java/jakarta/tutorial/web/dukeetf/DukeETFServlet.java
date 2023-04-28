/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.web.dukeetf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/dukeetf"}, asyncSupported=true)
public class DukeETFServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger("DukeETFServlet");
    private static final long serialVersionUID = 2114153638027156979L;
    private Queue<AsyncContext> requestQueue;
    @EJB private PriceVolumeBean pvbean; 
    
    @Override
    public void init(ServletConfig config) {
        /* Queue for requests */
        requestQueue = new ConcurrentLinkedQueue<>();
        /* Register with the bean that provides price/volume updates */
        pvbean.registerServlet(this);
    }
    
    /* PriceVolumeBean calls this method every second to send updates */
    public void send(double price, int volume) {
        /* Send update to all connected clients */
        for (AsyncContext acontext : requestQueue) {
            try {
                String msg = String.format("%.2f / %d", price, volume);
                PrintWriter writer = acontext.getResponse().getWriter();
                writer.write(msg);
                logger.log(Level.INFO, "Sent: {0}", msg);
                /* Close the connection
                 * The client (JavaScript) makes a new one instantly */
                acontext.complete();
            } catch (IOException ex) {
                logger.log(Level.INFO, ex.toString());
            }
        }
    }
    
    /* Service method */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        /* Put request in async mode. */
        final AsyncContext acontext = request.startAsync();
        /* Remove from the queue when done */
        acontext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
                logger.log(Level.INFO, "Connection closed.");
            }
            @Override
            public void onTimeout(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
                logger.log(Level.INFO, "Connection timeout.");
            }
            @Override
            public void onError(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
                logger.log(Level.INFO, "Connection error.");
            }
            @Override
            public void onStartAsync(AsyncEvent ae) throws IOException { }
        });
        /* Add to the queue */
        requestQueue.add(acontext);
        logger.log(Level.INFO, "Connection open.");
    }
}
