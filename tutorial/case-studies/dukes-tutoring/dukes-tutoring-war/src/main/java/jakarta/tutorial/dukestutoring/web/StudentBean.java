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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakartaee.tutorial.dukestutoring.entity.Student;

@Named
@RequestScoped
public class StudentBean {

    @Inject
    private AdminBean adminBean;
    private AdminManager adminManager;
    private Student student;
    private Student currentStudent;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    private String nickname;
    private String suffix;
    private String school;
    private int grade;
    protected String email;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}",
            message = "{invalid.phonenumber}")
    private String homePhone;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}",
            message = "{invalid.phonenumber}")
    private String mobilePhone;

    public StudentBean() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getCurrentStudent() {
        currentStudent = adminManager.currentStudent;
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String submit() {

        this.student = adminBean.createStudent(firstName, middleName,
                lastName, nickname, suffix, school, grade, email, homePhone,
                mobilePhone);
        return "createdStudent";
    }
}
