/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.customer.data;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ievans
 */
@Entity
@Table(name="CUSTOMER_CUSTOMER")
@NamedQuery(
    name="findAllCustomers",
    query="SELECT c FROM Customer c " +
          "ORDER BY c.id"
)
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Serializable {
    private static final Logger logger = 
            Logger.getLogger(Customer.class.getName());
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(required=true) 
    protected int id;
    
    @XmlElement(required=true) 
    protected String firstname;
    
    @XmlElement(required=true) 
    protected String lastname;
    
    @XmlElement(required=true)
    @OneToOne
    protected Address address;
    
    @XmlElement(required=true)
    protected String email;
 
    @XmlElement (required=true)
    protected String phone;
    
    public Customer() { 
        address = new Address();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        logger.log(Level.INFO, "setId called and set to {0}", id);
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        logger.log(Level.INFO, "setFirstname called and set to {0}", firstname);
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        logger.log(Level.INFO, "setLastname called and set to {0}", lastName);
        this.lastname = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        logger.log(Level.INFO, "setAddress called");
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        logger.log(Level.INFO, "setEmail called and set to {0}", email);
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        logger.log(Level.INFO, "setPhone called and set to {0}", phone);
        this.phone = phone;
    }
}
