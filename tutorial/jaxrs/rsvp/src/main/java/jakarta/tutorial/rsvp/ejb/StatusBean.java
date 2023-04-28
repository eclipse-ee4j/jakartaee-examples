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

import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.tutorial.rsvp.entity.Event;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author ievans
 */
@Stateless
@Named
@Path("/status")
public class StatusBean {

    private List<Event> allCurrentEvents;
    private static final Logger logger = Logger.getLogger("jakarta.tutorial.rsvp.ejb.StatusBean");

    @PersistenceContext
    private EntityManager em;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{eventId}/")
    public Event getEvent(@PathParam("eventId") Long eventId) {
        Event event = em.find(Event.class, eventId);
        return event;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<Event> getAllCurrentEvents() {
        logger.info("Calling getAllCurrentEvents");
        this.allCurrentEvents = (List<Event>)
                em.createNamedQuery("rsvp.entity.Event.getAllUpcomingEvents").getResultList();
        if (this.allCurrentEvents == null) {
            logger.warning("No current events!");
        }
        return this.allCurrentEvents;
    }

    public void setAllCurrentEvents(List<Event> events) {
        this.allCurrentEvents = events;
    }
}
