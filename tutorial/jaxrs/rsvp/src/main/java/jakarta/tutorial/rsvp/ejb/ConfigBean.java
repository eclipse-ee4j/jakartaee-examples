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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.tutorial.rsvp.entity.Event;
import jakarta.tutorial.rsvp.entity.Person;
import jakarta.tutorial.rsvp.entity.Response;
import jakarta.tutorial.rsvp.util.ResponseEnum;

/**
 *
 * @author ievans
 */
@Singleton
@Startup
public class ConfigBean {

    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger("jakarta.tutorial.rsvp.ejb.ConfigBean");

    @PostConstruct
    public void init() {
        // create the event owner
        Person dad = new Person();
        dad.setFirstName("Father");
        dad.setLastName("OfJava");
        em.persist(dad);

        // create the event
        Event event = new Event();
        event.setName("Duke's Birthday Party");
        event.setLocation("Top of the Mark");
        Calendar cal = new GregorianCalendar(2010, Calendar.MAY, 23, 19, 0);
        event.setEventDate(cal.getTime());
        em.persist(event);

        // set the relationships
        dad.getOwnedEvents().add(event);
        dad.getEvents().add(event);
        event.setOwner(dad);
        event.getInvitees().add(dad);
        Response dadsResponse = new Response(event, dad, ResponseEnum.ATTENDING);
        em.persist(dadsResponse);
        event.getResponses().add(dadsResponse);

        // create some invitees
        Person duke = new Person();
        duke.setFirstName("Duke");
        duke.setLastName("OfJava");
        em.persist(duke);
        
        Person tux = new Person();
        tux.setFirstName("Tux");
        tux.setLastName("Penguin");
        em.persist(tux);

        // set the relationships
        event.getInvitees().add(duke);
        duke.getEvents().add(event);
        Response dukesResponse = new Response(event, duke);
        em.persist(dukesResponse);
        event.getResponses().add(dukesResponse);
        duke.getResponses().add(dukesResponse);

        event.getInvitees().add(tux);
        tux.getEvents().add(event);
        Response tuxsResponse = new Response(event, tux);
        em.persist(tuxsResponse);
        event.getResponses().add(tuxsResponse);
        tux.getResponses().add(tuxsResponse);

    }
    
}
