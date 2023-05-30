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
package jakartaee.examples.websocket.onclose;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

import org.glassfish.tyrus.client.ClientManager;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakartaee.examples.utils.ITBase;

/**
 * A JUnit test for the @OnError example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ClientEndpoint
@RunWith(Arquillian.class)
public class OnCloseEndpointIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Stores our buffer.
     */
    private final StringBuilder buffer = new StringBuilder();

    /**
     * Stores our countdown latch.
     */
    private CountDownLatch countDown = new CountDownLatch(1);

    /**
     * Get the buffer.
     *
     * @return the buffer.
     */
    public String getBuffer() {
        return buffer.toString();
    }

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param message the message.
     * @throws Exception when a serious error occurs.
     */
    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        session.getBasicRemote().sendText(message);
    }

    /**
     * Handle the onClose.
     *
     * @param session the session.
     * @throws IOException when an I/O error occurs.
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        buffer.append("Closing connection");
        countDown.countDown();
    }

    /**
     * Test the client endpoint.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    @RunAsClient
    public void testClientEndpoint() throws Exception {
        countDown = new CountDownLatch(1);

        ClientManager client = ClientManager.createClient();
        StringBuilder wsUrl = new StringBuilder();
        wsUrl.append("ws://")
             .append(baseUrl.getHost())
             .append(":")
             .append(baseUrl.getPort())
             .append(baseUrl.getPath())
             .append("echo");

        client.connectToServer(this, new URI(wsUrl.toString()));

        countDown.await(10, SECONDS);
        System.out.println(buffer.toString());
        assertTrue(buffer.toString().contains("Closing connection"));
    }
}
