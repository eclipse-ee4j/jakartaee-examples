/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jakarta.tutorial.addressbook.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author ian
 */
@Entity
public class Contact implements Serializable {

    private static final long serialVersionUID = -825634229676522580L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?",
            message = "{invalid.email}")
    protected String email;
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "{invalid.phonenumber}")
    protected String mobilePhone;
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "{invalid.phonenumber}")
    protected String homePhone;
    @Temporal(TemporalType.DATE)
    @Past
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
     * Get the value of homePhone
     *
     * @return the value of homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Set the value of homePhone
     *
     * @param homePhone new value of homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * Get the value of mobilePhone
     *
     * @return the value of mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Set the value of mobilePhone
     *
     * @param mobilePhone new value of mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "addressbook.entity.Contact[id=" + id + "]";
    }

}
