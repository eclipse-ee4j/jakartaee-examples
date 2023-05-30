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

import com.gargoylesoftware.htmlunit.IncorrectnessListener;

/**
 * An ignoring incorrectness listener.
 *
 * <p>
 *  This incorrectness listener ignores any incorrectness.
 * </p>
 */
public class IgnoringIncorrectnessListener implements IncorrectnessListener {

    @Override
    public void notify(String message, Object origin) {

    }

}
