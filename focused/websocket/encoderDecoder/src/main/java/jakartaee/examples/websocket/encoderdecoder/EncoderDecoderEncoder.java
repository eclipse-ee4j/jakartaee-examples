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

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

/**
 * The Encoder.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class EncoderDecoderEncoder implements Encoder.Text<EncoderDecoder>{

    /**
     * Encode.
     * 
     * @param encoderDecoder the object.
     * @return the string.
     * @throws EncodeException when an encode error occurs.
     */
    @Override
    public String encode(EncoderDecoder encoderDecoder) throws EncodeException {
        return encoderDecoder.toString();
    }

    /**
     * Initialize the encoder.
     * 
     * @param endpointConfig the endpoint configuration. 
     */
    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    /**
     * Destroy the encoder.
     */
    @Override
    public void destroy() {
    }
}
