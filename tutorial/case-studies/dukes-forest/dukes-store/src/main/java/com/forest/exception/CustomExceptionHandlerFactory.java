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
package com.forest.exception;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author markito
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

   private ExceptionHandlerFactory parent;

   // this injection handles jsf
   public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
    this.parent = parent;
   }
  
    @Override
    public ExceptionHandler getExceptionHandler() {
        
        ExceptionHandler handler = new CustomExceptionHandler(parent.getExceptionHandler());
        
        return handler;
    }
    
}
