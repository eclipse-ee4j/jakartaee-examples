/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.guessnumber;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class Generator implements Serializable {

   private static final long serialVersionUID = -7213673465118041882L;

   private final java.util.Random random = 
       new java.util.Random( System.currentTimeMillis() );

   private final int maxNumber = 100;

   java.util.Random getRandom() {
       return random;
   }

   @Produces @Random int next() {
       return getRandom().nextInt(maxNumber + 1);
   }

   @Produces @MaxNumber int getMaxNumber() {
       return maxNumber;
   }

}

