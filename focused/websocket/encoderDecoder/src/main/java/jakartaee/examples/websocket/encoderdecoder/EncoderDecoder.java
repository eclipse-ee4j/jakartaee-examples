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

/**
 * The Encoder/Decoder class.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class EncoderDecoder {

    /**
     * Stores the string.
     */
    private final String string;
    
    /**
     * Constructor.
     * 
     * @param string the string.
     */
    public EncoderDecoder(String string) {
        this.string = string;
    }

    /**
     * Return the string representation.
     * 
     * @return the string representation.
     */
    @Override
    public String toString() {
        return string;
    }
}
