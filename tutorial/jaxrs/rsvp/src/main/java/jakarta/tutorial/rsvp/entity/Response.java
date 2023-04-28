/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.rsvp.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.tutorial.rsvp.util.ResponseEnum;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@NamedQuery(name="rsvp.entity.Response.findResponseByEventAndPerson",
            query="SELECT r " + 
            "FROM Response r " +
            "JOIN r.event e " +
            "JOIN r.person p " +
            "WHERE e.id = :eventId AND p.id = :personId")

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Response implements Serializable {
    private static final long serialVersionUID = -8188690304868834266L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @XmlTransient
    private Event event;
    @ManyToOne
    private Person person;
    @Enumerated(EnumType.STRING)
    private ResponseEnum response;

    public Response() {
        this.response = ResponseEnum.NOT_RESPONDED;
    }

    public Response(Event event, Person person, ResponseEnum response) {
        this.event = event;
        this.person = person;
        this.response = response;
    }

    public Response(Event event, Person person) {
        this.event = event;
        this.person = person;
        this.response = ResponseEnum.NOT_RESPONDED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rsvp.entity.Response[id=" + id + "]";
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
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return the response
     */
    public ResponseEnum getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(ResponseEnum response) {
        this.response = response;
    }

    public String getResponseText() {
        return response.getLabel();
    }
}
