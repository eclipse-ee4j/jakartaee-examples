/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukestutoring.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.dukestutoring.ejb.AdminBean;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakartaee.tutorial.dukestutoring.entity.Address;
import jakartaee.tutorial.dukestutoring.entity.Student;

@Named
@RequestScoped
public class AddressBean {

    @Inject
    private AdminBean adminBean;
    private Address address;
    @NotNull
    private String street1;
    private String street2;
    @NotNull
    private String city;
    @NotNull
    private String province;
    private String country;
    @NotNull
    @Digits(integer = 5, fraction = 0, message = "{invalid.zipcode}")
    private String postalCode;
    private boolean isPrimary;

    /**
     * Creates a new instance of AddressBean
     */
    public AddressBean() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String submit(Student student) {

        this.address = adminBean.createAddress(street1, street2, city, province,
                country, postalCode, isPrimary, student);
        return "createdAddress";
    }
}
