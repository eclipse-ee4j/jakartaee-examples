/*
 * Copyright (c) 2014, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.traffic.rar;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.transaction.xa.XAResource;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ActivationSpec;
import jakarta.resource.spi.BootstrapContext;
import jakarta.resource.spi.Connector;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterInternalException;
import jakarta.resource.spi.endpoint.MessageEndpoint;
import jakarta.resource.spi.endpoint.MessageEndpointFactory;
import jakarta.resource.spi.work.Work;
import jakarta.resource.spi.work.WorkException;
import jakarta.resource.spi.work.WorkManager;
import jakarta.tutorial.traffic.rar.inbound.ObtainEndpointWork;
import jakarta.tutorial.traffic.rar.inbound.TrafficActivationSpec;
import jakarta.tutorial.traffic.rar.inbound.TrafficServiceSubscriber;

@Connector(
        displayName = "TrafficRA",
        vendorName = "Jakarta EE Tutorial", 
        version = "9.0"
)
public class TrafficResourceAdapter implements ResourceAdapter, Serializable {
    
    private static final Logger log = Logger.getLogger("TrafficResourceAdapter");
    private static final long serialVersionUID = -2195736837440941558L;
    private TrafficActivationSpec tSpec;
    private WorkManager workManager;
    private Work tSubscriber;
    
    /* Make the activation configuration available elswhere */
    public TrafficActivationSpec getActivationSpec() {
        return tSpec;
    }
    
    @Override
    public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
        log.info("[TrafficResourceAdapter] start()");
        /* Get the work manager from the container to submit tasks to
         * be executed in container-managed threads */
        workManager = ctx.getWorkManager();
    }

    @Override
    public void stop() {
        log.info("[TrafficResourceAdapter] stop()");
    }

    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory, 
                                   ActivationSpec spec) 
                                   throws ResourceException {
        
        log.info("[TrafficResourceAdapter] endpointActivation()"); 
        tSpec = (TrafficActivationSpec) spec;
        /* New in JCA 1.7 - Get the endpoint class implementation (i.e. the
         * MDB class). This allows looking at the MDB implementation for
         * annotations. */
        Class endpointClass = endpointFactory.getEndpointClass();
        tSpec.setBeanClass(endpointClass);
        tSpec.findCommandsInMDB();
        
        /* MessageEndpoint msgEndpoint = endpointFactory.createEndpoint(null);
         * but we need to do that in a different thread, otherwise the MDB
         * never deploys. */
        ObtainEndpointWork work = new ObtainEndpointWork(this, endpointFactory);
        workManager.scheduleWork(work);      
    }
    
    /* Called from ObtainEndpoint work after obtaining the endpoint */
    public void endpointAvailable(MessageEndpoint endpoint) {
        
        try {
            /* Start the traffic subscriber client in a new thread */
            tSubscriber = new TrafficServiceSubscriber(tSpec, endpoint);
            workManager.scheduleWork(tSubscriber);
        } catch (WorkException e) {
            log.info("[TrafficResourceAdapter] Can't start the subscriber");
            log.info(e.getMessage());
        }
    }
    
    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory, 
                                     ActivationSpec spec) {
        log.info("[TrafficResourceAdapter] endpointDeactivation()");
        /* Stop listening */
        tSubscriber.release();
    }

    /* This connector does not use transactions */
    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) 
                                       throws ResourceException {
        return null;
    }
    
}
