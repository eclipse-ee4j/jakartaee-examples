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
package jakartaee.examples.faces.actionlistener;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;

/**
 * The ActionListener for the f:actionListener example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class ActionListenerExample implements ActionListener {

    /**
     * Process the action.
     * 
     * @param event the event.
     * @throws AbortProcessingException when asked to abort the rest of the processing.
     */
    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        throw new AbortProcessingException("We stopped here!");
    }   
}
