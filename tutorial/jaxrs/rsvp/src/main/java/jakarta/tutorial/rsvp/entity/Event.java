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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;


@NamedQuery(name="rsvp.entity.Event.getAllUpcomingEvents",
            query="SELECT e FROM Event e ")
@XmlRootElement(name = "Event")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {
    private static final long serialVersionUID = -5584404843358199527L;
    @OneToMany(mappedBy = "event")
    private List<Response> responses;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    protected List<Person> invitees;
    protected String name;
    @ManyToOne
    private Person owner;
    protected String location;
    @Temporal(TemporalType.DATE)
    private Date eventDate;

    public Event() {
        this.invitees = new ArrayList<>();
        this.responses = new ArrayList<>();
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Get the value of invitees
     *
     * @return the value of invitees
     */
    public List<Person> getInvitees() {
        return invitees;
    }

    /**
     * Set the value of invitees
     *
     * @param invitees new value of invitees
     */
    public void setInvitees(List<Person> invitees) {
        this.invitees = invitees;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rsvp.entity.Event[id=" + id + "]";
    }

    /**
     * @return the responses
     */
    public List<Response> getResponses() {
        return responses;
    }

    /**
     * @param responses the responses to set
     */
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the owner
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

}
