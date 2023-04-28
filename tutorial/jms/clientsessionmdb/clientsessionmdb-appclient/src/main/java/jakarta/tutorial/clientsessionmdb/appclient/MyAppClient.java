/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.clientsessionmdb.appclient;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.tutorial.clientsessionmdb.sb.PublisherRemote;

/**
 * The MyAppClient class is the client program for this application. It calls
 * the publisher's publishNews method twice.
 */
public class MyAppClient {

    static final Logger logger = Logger.getLogger("MyAppClient");
    
    @EJB
    private static PublisherRemote publisher;

    public MyAppClient(String[] args) {
    }

    public static void main(String[] args) {
        MyAppClient client = new MyAppClient(args);
        client.doTest();
        System.exit(0);
    }

    public void doTest() {
        try {
            publisher.publishNews();
            publisher.publishNews();
            System.out.println("To view the bean output,");
            System.out.println(
                    " check <install_dir>/domains/domain1/logs/server.log.");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception:{0}", ex.toString());
        }
    }
}
