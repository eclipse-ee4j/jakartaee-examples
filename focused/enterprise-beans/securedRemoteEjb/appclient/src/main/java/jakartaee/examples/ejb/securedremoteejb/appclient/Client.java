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
package jakartaee.examples.ejb.securedremoteejb.appclient;

import jakartaee.examples.ejb.securedremoteejb.SecuredRemoteEjb;
import jakarta.ejb.EJB;

/**
 * A secured remote EJB application client.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class Client {

    /**
     * Stores the remote EJB.
     */
    @EJB
    private static SecuredRemoteEjb ejb;

    /**
     * Run method.
     */
    public void run() {
        System.out.println(ejb.helloWorld());
    }

    /**
     * Main method.
     * 
     * <p>
     *  If you are executing this on Glassfish / Payara deploy the EAR to the 
     *  server first then build the client application, and then go into the
     *  target directory and execute the command line mentioned below.
     * </p>
     *
     * <pre>
     *   appclient -client appclient-8-SNAPSHOT.jar -user securedRemoteEjbUser -password password
     * </pre>
     * 
     * <p>
     *  Note this assumes you have setup the username 'securedRemoteEjbUser' 
     *  with password 'password' in the 'file' realm.
     * </p>
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        Client main = new Client();
        main.run();
    }
}
