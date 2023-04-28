/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.customer.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;
import jakarta.tutorial.customer.data.Customer;

/**
 *
 * @author ievans
 */
@Model
public class CustomerManager implements Serializable {
    private Customer customer;
    private List<Customer> customers;
    private static final Logger logger = Logger.getLogger(CustomerManager.class.getName());
    @EJB
    private CustomerBean customerBean;
    
    @PostConstruct
    private void init() {
        logger.info("new customer created");
        customer = new Customer();
        setCustomers(customerBean.retrieveAllCustomers());
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customerBean.retrieveAllCustomers();
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
