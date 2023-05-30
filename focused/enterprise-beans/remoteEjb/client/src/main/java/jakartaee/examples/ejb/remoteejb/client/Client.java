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
package jakartaee.examples.ejb.remoteejb.client;

import jakartaee.examples.ejb.remoteejb.RemoteEjb;
import javax.naming.InitialContext;

/**
 * The remoteEjb client.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class Client {

    /**
     * Main method.
     * 
     * <p>
     *  If you are running this on Glassfish/Payara and you want to connect to
     *  a specific host the following properties would need to be used with the
     *  InitialContext.
     * </p>
     * <pre>
     *  properties.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
     *  properties.put("org.omg.CORBA.ORBInitialHost", "localhost");
     *  properties.put("org.omg.CORBA.ORBInitialPort", "3700");
     * </pre>
     *
     * @param arguments the arguments.
     * @throws Exception when an error occurs.
     */
    public static void main(String[] arguments) throws Exception {
        InitialContext initialContext = new InitialContext();
        RemoteEjb ejb = (RemoteEjb) initialContext.lookup("java:global/remoteEjb/remoteEjbJar/remoteEjb");
        System.out.println(ejb.helloWorld());
    }
}
