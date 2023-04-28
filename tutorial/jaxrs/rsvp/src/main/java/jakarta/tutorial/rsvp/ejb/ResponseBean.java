/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.rsvp.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.tutorial.rsvp.entity.Response;
import jakarta.tutorial.rsvp.util.ResponseEnum;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author ievans
 */
@Stateless
@Path("/{eventId}/{inviteId}")
public class ResponseBean {

    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ResponseBean.class.getName());
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getResponse(@PathParam("eventId") Long eventId,
            @PathParam("inviteId") Long personId) {
        Response response = (Response)
                em.createNamedQuery("rsvp.entity.Response.findResponseByEventAndPerson")
                .setParameter("eventId", eventId)
                .setParameter("personId", personId)
                .getSingleResult();
        return response;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void putResponse(String userResponse,
            @PathParam("eventId") Long eventId,
            @PathParam("inviteId") Long personId) {
        logger.log(Level.INFO, 
                "Updating status to {0} for person ID {1} for event ID {2}", 
                new Object[]{userResponse, 
                    eventId, 
                    personId});
         Response response = (Response)
                em.createNamedQuery("rsvp.entity.Response.findResponseByEventAndPerson")
                .setParameter("eventId", eventId)
                .setParameter("personId", personId)
                .getSingleResult();
        if (userResponse.equals(ResponseEnum.ATTENDING.getLabel()) && !response.getResponse().equals(ResponseEnum.ATTENDING)) {
            response.setResponse(ResponseEnum.ATTENDING);
            em.merge(response);
        } else if (userResponse.equals(ResponseEnum.NOT_ATTENDING.getLabel()) && !response.getResponse().equals(ResponseEnum.NOT_ATTENDING)) {
            response.setResponse(ResponseEnum.NOT_ATTENDING);
            em.merge(response);
        } else if (userResponse.equals(ResponseEnum.MAYBE_ATTENDING.getLabel()) && !response.getResponse().equals(ResponseEnum.MAYBE_ATTENDING)) {
            response.setResponse(ResponseEnum.MAYBE_ATTENDING);
            em.merge(response);
        }
    }
 
}
