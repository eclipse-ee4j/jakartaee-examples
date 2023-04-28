/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.rsvp.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.rsvp.entity.Event;
import jakarta.tutorial.rsvp.entity.Person;
import jakarta.tutorial.rsvp.util.ResponseEnum;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author ievans
 */
@Named
@SessionScoped
public class StatusManager implements Serializable {

    private static final long serialVersionUID = 1;
    private static final Logger logger = Logger.getLogger(StatusManager.class.getName());
    private Event event;
    private List<Event> events;
    private Client client;
    private final String baseUri = "http://localhost:8080/rsvp/webapi";
    private WebTarget target;

    /**
     * Default constructor creates the JAX-RS client
     */
    public StatusManager() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    private void clean() {
        client.close();
    }

    /**
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Sets the event
     *
     * @param event the current event
     * @return a JSF action string
     */
    public String getEventStatus(Event event) {
        this.setEvent(event);
        return "eventStatus";
    }

    /**
     * Get all the events
     *
     * @return all the events
     */
    public List<Event> getEvents() {
        List<Event> returnedEvents = null;
        try {
            returnedEvents = client.target(baseUri)
                    .path("/status/all")
                    .request(MediaType.APPLICATION_XML)
                    .get(new GenericType<List<Event>>() {
                    });
            if (returnedEvents == null) {
                logger.log(Level.SEVERE, "Returned events null.");
            } else {
                logger.log(Level.INFO, "Events have been returned.");
            }
        } catch (WebApplicationException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (ResponseProcessingException ex) {
            logger.log(Level.SEVERE, "ReponseProcessingException thrown.");
            logger.log(Level.SEVERE, "Error is {0}", ex.getMessage());
        } catch (ProcessingException ex) {
            logger.log(Level.SEVERE, "ProcessingException thrown.");
            logger.log(Level.SEVERE, "Error is {0}", ex.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error retrieving all events.");
            logger.log(Level.SEVERE, "base URI is {0}", baseUri);
            logger.log(Level.SEVERE, "path is {0}", "all");
            logger.log(Level.SEVERE, "Exception is {0}", ex.getMessage());
        }
        return returnedEvents;
    }

    /**
     * Setter for all the events
     *
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Retrieve the status values
     *
     * @return an array of response values
     */
    public ResponseEnum[] getStatusValues() {
        return ResponseEnum.values();
    }

    /**
     * Change the status of a user's response
     *
     * @param userResponse the new response
     * @param person the attendee
     * @param event the event
     * @return the navigation case
     */
    public String changeStatus(ResponseEnum userResponse, Person person, Event event) {
        String navigation;
        try {
            logger.log(Level.INFO,
                    "changing status to {0} for {1} {2} for event ID {3}.",
                    new Object[]{userResponse,
                        person.getFirstName(),
                        person.getLastName(),
                        event.getId().toString()});
            client.target(baseUri)
                    .path(event.getId().toString())
                    .path(person.getId().toString())
                    .request(MediaType.APPLICATION_XML)
                    .post(Entity.xml(userResponse.getLabel()));
            navigation = "changedStatus";
        } catch (ResponseProcessingException ex) {
            logger.log(Level.WARNING, "couldn''t change status for {0} {1}",
                    new Object[]{person.getFirstName(),
                        person.getLastName()});
            logger.log(Level.WARNING, ex.getMessage());
            navigation = "error";
        }
        return navigation;
    }
}
