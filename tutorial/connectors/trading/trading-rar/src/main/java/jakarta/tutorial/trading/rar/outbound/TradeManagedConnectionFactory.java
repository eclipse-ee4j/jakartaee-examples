/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.trading.rar.outbound;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.security.auth.Subject;

import jakarta.resource.Referenceable;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConfigProperty;
import jakarta.resource.spi.ConnectionDefinition;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionFactory;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterAssociation;
import jakarta.tutorial.trading.rar.api.TradeConnection;
import jakarta.tutorial.trading.rar.api.TradeConnectionFactory;


/* The container's connection manager uses this class to create a pool
 * of managed connections, which are associated at times with physical ones */

/* Define classes an interfaces for the EIS physical connection */
@ConnectionDefinition(
    connectionFactory = TradeConnectionFactory.class,
    connectionFactoryImpl = TradeConnectionFactoryImpl.class,
    connection = TradeConnection.class,
    connectionImpl = TradeConnectionImpl.class
)
public class TradeManagedConnectionFactory implements ManagedConnectionFactory,
                                                      ResourceAdapterAssociation,
                                                      Serializable,
                                                      Referenceable {
    
    private static final Logger log = Logger.getLogger("TradeManagedConnectionFactory");
    private static final long serialVersionUID = 7918855339952421358L;
    private ResourceAdapter ra;
    private Reference reference;
    private PrintWriter logWriter;
    private String host;
    private String port;
    
    public TradeManagedConnectionFactory() { }
    
    /* Configuration properties */
    @ConfigProperty(defaultValue = "localhost")
    public void setHost(String host) { this.host = host; }
    public String getHost() { return host; }
    @ConfigProperty(type = String.class, defaultValue = "4004")
    public void setPort(String port) { this.port = port; }
    public String getPort() { return port; }
    
    @Override
    public Object createConnectionFactory() throws ResourceException {
        log.info("[TradeManagedConnectionFactory] createConnectionFactory()-NM");
        /* Non-managed connections not supported */
        return null;
    }
    
    /* The application server provides a connection manager */
    @Override
    public Object createConnectionFactory(ConnectionManager cxManager) 
                                          throws ResourceException {
        log.info("[TradeManagedConnectionFactory] createConnectionFactory()");
        return new TradeConnectionFactoryImpl(this, cxManager);
    }

    /* Called by the application server to create a managed connection */
    @Override
    public ManagedConnection createManagedConnection(Subject subject, 
                                                     ConnectionRequestInfo cxRequestInfo) 
                                                     throws ResourceException {
        log.info("[TradeManagedConnectionFactory] createManagedConnection()");
        try {
            return new TradeManagedConnection(getHost(), getPort());
        } catch (IOException e) {
            throw new ResourceException(e.getCause());
        }
    }

    /* When an application requests a connection, provide an existing one
     * from the pool if it matches the connection parameters */
    @Override
    public ManagedConnection matchManagedConnections(Set connectionSet, 
                                                     Subject subject, 
                                                     ConnectionRequestInfo cxRequestInfo) 
                                                     throws ResourceException {
        log.info("[TradeManagedConnectionFactory] matchManagedConnections()");
        /* This resource adapter does not use security (Subject) */
        TradeManagedConnection match = null;
        /* This resource adapter has no additional parameters for connections,
         * so any open connection can be used by an application */
        for (Object mco : connectionSet) {
            if (mco != null) { 
                match = (TradeManagedConnection) mco;
                log.info("Connection match!");
                break;
            }
        }
        return match;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.logWriter = out;
    }
    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logWriter;
    }

    /* Called by the server to associate this object with the resource adapter */
    @Override
    public ResourceAdapter getResourceAdapter() {
        return ra;
    }
    @Override
    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        this.ra = ra;
    }

    /* Called by the server to associate an RA with a JNDI resource name.
     * Applications access an RA via JNDI resource injection (@Resource) */
    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }
    @Override
    public Reference getReference() throws NamingException {
        return reference;
    }
    
}
