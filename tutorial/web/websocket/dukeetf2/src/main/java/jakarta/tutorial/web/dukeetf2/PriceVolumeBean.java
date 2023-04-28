/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.web.dukeetf2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

/* Updates price and volume information every second */
@Startup
@Singleton
public class PriceVolumeBean {
    /* Use the container's timer service */
    @Resource TimerService tservice;
    private Random random;
    private volatile double price = 100.0;
    private volatile int volume = 300000;
    private static final Logger logger = Logger.getLogger("PriceVolumeBean");
    
    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        logger.log(Level.INFO, "Initializing EJB.");
        random = new Random();
        tservice.createIntervalTimer(1000, 1000, new TimerConfig());
    }
    
    @Timeout
    public void timeout() {
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        ETFEndpoint.send(price, volume);
    }
}
