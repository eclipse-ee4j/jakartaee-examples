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
package jakartaee.examples.websocket.encoderdecoder;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.glassfish.tyrus.client.ClientManager;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakartaee.examples.utils.ITBase;

/**
 * An annotated ClientEndpoint for the annotated ClientEndpoint example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ClientEndpoint(
    encoders = EncoderDecoderEncoder.class,
    decoders = EncoderDecoderDecoder.class
)
@RunWith(Arquillian.class)
public class EncoderDecoderEndpointIT extends ITBase {

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
     * Handle the on open event.
     *
     * @param session the session.
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendObject(new EncoderDecoder("ECHO"));
        } catch (IOException | EncodeException e) {
            buffer.append(e.getMessage());
        }
    }

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param encoderDecoder the message.
     */
    @OnMessage
    public void onMessage(Session session, EncoderDecoder encoderDecoder) {
        buffer.append(encoderDecoder.toString());
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

        countDown.await(100, TimeUnit.SECONDS);

        assertEquals("ECHO", buffer.toString());
    }
}
