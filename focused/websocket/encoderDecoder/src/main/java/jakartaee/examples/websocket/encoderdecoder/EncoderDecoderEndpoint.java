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

import jakarta.websocket.OnMessage;
import jakarta.websocket.server.ServerEndpoint;

/**
 * An Encoder/Decoder example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ServerEndpoint(
        encoders = {EncoderDecoderEncoder.class},
        decoders = {EncoderDecoderDecoder.class},
        value="/echo")
public class EncoderDecoderEndpoint {

    /**
     * Handle the Encoder/Decoder message.
     *
     * @param message the message.
     * @return the message.
     */
    @OnMessage
    public EncoderDecoder onMessage(EncoderDecoder message) {
        return message;
    }
}
