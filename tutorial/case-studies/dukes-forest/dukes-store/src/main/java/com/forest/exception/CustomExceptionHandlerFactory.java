/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
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
