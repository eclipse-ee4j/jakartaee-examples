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

package jakarta.tutorial.dukestutoring.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.dukestutoring.ejb.AdminBean;
import jakartaee.tutorial.dukestutoring.entity.Address;
import jakartaee.tutorial.dukestutoring.entity.Guardian;
import jakartaee.tutorial.dukestutoring.entity.Student;

/**
 *
 * @author ian
 */
@Named
@SessionScoped
public class AdminManager implements Serializable {
    private static final long serialVersionUID = 7090138834846165429L;
    protected Student currentStudent;
    protected Address currentAddress;
    protected Guardian currentGuardian;
    private Map<String, Integer> allGrades;

    @EJB
    private AdminBean adminBean;

    /**
     * Get the value of currentGuardian
     *
     * @return the value of currentGuardian
     */
    public Guardian getCurrentGuardian() {
        return currentGuardian;
    }

    /**
     * Set the value of currentGuardian
     *
     * @param currentGuardian new value of currentGuardian
     */
    public void setCurrentGuardian(Guardian currentGuardian) {
        this.currentGuardian = currentGuardian;
    }


    /**
     * Get the value of currentAddress
     *
     * @return the value of currentAddress
     */
    public Address getCurrentAddress() {
        return currentAddress;
    }

    /**
     * Set the value of currentAddress
     *
     * @param currentAddress new value of currentAddress
     */
    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }


    /**
     * Get the value of currentStudent
     *
     * @return the value of currentStudent
     */
    public Student getCurrentStudent() {
        return currentStudent;
    }

    /**
     * Set the value of currentStudent
     *
     * @param currentStudent new value of currentStudent
     */
    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public void setCurrentStudentById(Long id) {
        this.currentStudent = adminBean.findStudentById(id);
    }

    /** Creates a new instance of AdminManager */
    public AdminManager() {
        this.currentAddress = new Address();
        this.currentGuardian = new Guardian();
        this.currentStudent = new Student();

        this.allGrades = new LinkedHashMap<>();
        this.allGrades.put("Kindergarten", new Integer(0));
        this.allGrades.put("First", new Integer(1));
        this.allGrades.put("Second", new Integer(2));
        this.allGrades.put("Third", new Integer(3));
        this.allGrades.put("Fourth", new Integer(4));
        this.allGrades.put("Fifth", new Integer(5));
        this.allGrades.put("Sixth", new Integer(6));
        this.allGrades.put("Seventh", new Integer(7));
        this.allGrades.put("Eighth", new Integer(8));
        this.allGrades.put("Ninth", new Integer(9));
        this.allGrades.put("Tenth", new Integer(10));
        this.allGrades.put("Eleventh", new Integer(11));
        this.allGrades.put("Twelfth", new Integer(12));
    }

    public String editStudent(Student student) {
        this.setCurrentStudent(student);
        return "editStudent";
    }

    public String createStudent() {
        this.setCurrentStudent(null);
        return "createStudent";
    }

    public String deleteStudent(Student student) {
        this.setCurrentStudent(student);
        return "deleteStudent";
    }

    public String editGuardian(Guardian guardian) {
        this.setCurrentGuardian(guardian);
        return "editGuardian";
    }

    public String createGuardian(Student student) {
        this.setCurrentStudent(student);
        this.setCurrentGuardian(null);
        return "createGuardian";
    }

    public String deleteGuardian(Guardian guardian) {
        this.setCurrentGuardian(guardian);
        return "deleteGuardian";
    }

    public String createStudentAddress(Student student) {
        this.setCurrentStudent(student);
        return "createAddress";
    }

    public String createGuardianAddress(Guardian guardian) {
        this.setCurrentGuardian(guardian);
        return "createAddress";
    }

    public String editAddress(Address address) {
        this.setCurrentAddress(address);
        return "editAddress";
    }

    public String deleteAddress(Address address) {
        this.setCurrentAddress(address);
        return "deleteAddress";
    }

    /**
     * @return the allGrades
     */
    public Map<String, Integer> getAllGrades() {
        return allGrades;
    }

    /**
     * @param allGrades the allGrades to set
     */
    public void setAllGrades(Map<String, Integer> allGrades) {
        this.allGrades = allGrades;
    }

}
