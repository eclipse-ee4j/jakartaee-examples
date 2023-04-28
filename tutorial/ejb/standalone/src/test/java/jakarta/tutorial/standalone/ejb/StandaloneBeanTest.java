/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.standalone.ejb;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ejb.embeddable.EJBContainer;

/**
 *
 * @author ian
 */
public class StandaloneBeanTest {

    private EJBContainer ec;
    private Context ctx;
    private static final Logger logger = Logger.getLogger("standalone.ejb");

    public StandaloneBeanTest() {
    }

    @Before
    public void setUp() {
        ec = EJBContainer.createEJBContainer();
        ctx = ec.getContext();
    }

    @After
    public void tearDown() {
        if (ec != null) {
            ec.close();
        }
    }

    /**
     * Test of returnMessage method, of class StandaloneBean.
     * @throws java.lang.Exception
     */
    @Test
    public void testReturnMessage() throws Exception {
        logger.info("Testing standalone.ejb.StandaloneBean.returnMessage()");
        StandaloneBean instance = 
                (StandaloneBean) ctx.lookup("java:global/classes/StandaloneBean");
        String expResult = "Greetings!";
        String result = instance.returnMessage();
        assertEquals(expResult, result);
    }
}
