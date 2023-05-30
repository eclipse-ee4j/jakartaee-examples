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
package jakartaee.examples.utils;

import com.gargoylesoftware.htmlunit.WebClient;

public class DebugOptions {

    public static void setDebugOptions(WebClient webClient) {
        if (System.getProperty("suspend") != null) {
            webClient.getOptions().setTimeout(0);
        }
    }

}
