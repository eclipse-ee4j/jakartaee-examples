/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.forest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ievans
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
    @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address"),
    @NamedQuery(name = "Person.findByCity", query = "SELECT p FROM Person p WHERE p.city = :city")})
public class Person implements Serializable {
    
    private static final long serialVersionUID = 6253057722726297688L;
    @JoinTable(name = "PERSON_GROUPS", joinColumns = {
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "GROUPS_ID", referencedColumnName = "ID")})
    @ManyToMany
    protected List<Groups> groupsList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Size(min=3, max=50, message="{person.firstname}")
    @Column(name = "FIRSTNAME")
    protected String firstname;
    @Basic(optional = false)
    @Size(min=3, max=100, message="{person.lastname}")
    @Column(name = "LASTNAME")
    protected String lastname;
    @Pattern(regexp = ".+@.+\\.[a-z]+", message= "{person.email}")
    @Size(min=3, max=45, message= "{person.email}")
    @Basic(optional = false)
    @Column(name = "EMAIL")
    protected String email;
    @Basic(optional = false)
    @Size(min=3, max=45, message= "{person.address}")
    @Column(name = "ADDRESS")
    protected String address;
    @Basic(optional = false)
    @Size(min=3, max=45, message= "{person.city}")
    @Column(name = "CITY")
    protected String city;
    @Basic(optional = false)
    @Size(min=7, max=100, message= "{person.password}")
    @Column(name = "PASSWORD")
    protected String password;

    public Person() {
        this.groupsList = new ArrayList<Groups>();
    }
    
    public Person(Integer id) {
        this.id = id;
        this.groupsList = new ArrayList<Groups>();
    }
    
    public Person(Integer id, 
            String firstName, 
            String lastName, 
            String email, 
            String address, 
            String city) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.groupsList = new ArrayList<Groups>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Add XmlTransient annotation to this field for security reasons. 
     * @return the password
     */
    @XmlTransient  
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.Person[ id=" + id + " ]";
    }

}
