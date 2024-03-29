/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakartaee.tutorial.dukestutoring.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Past;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
public class PersonDetails implements Serializable {
    private static final long serialVersionUID = 2921499531009567911L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "details", cascade=CascadeType.ALL)
    protected Person person;
    @Lob
    @XmlTransient
    protected byte[] photo;
    @Past
    @Temporal(TemporalType.DATE)
    protected Date birthday;

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the value of birthday
     *
     * @param birthday new value of birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    /**
     * Get the value of photo
     *
     * @return the value of photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Set the value of photo
     *
     * @param photo new value of photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    /**
     * Get the value of person
     *
     * @return the value of person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Set the value of person
     *
     * @param person new value of person
     */
    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof PersonDetails)) {
            return false;
        }
        PersonDetails other = (PersonDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.PersonDetails[id=" + id + "]";
    }

}
