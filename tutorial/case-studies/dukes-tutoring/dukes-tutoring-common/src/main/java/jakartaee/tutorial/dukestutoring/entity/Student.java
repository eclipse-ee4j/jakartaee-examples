/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakartaee.tutorial.dukestutoring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.tutorial.dukestutoring.util.StatusType;
import jakarta.validation.constraints.Digits;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@NamedQueries({
    @NamedQuery(
        name = "Student.findAllStudents",
        query = "SELECT s FROM Student s " +
                "ORDER BY s.lastName"),
    @NamedQuery(
        name = "Student.findStudentsByStatus",
        query = "SELECT s FROM Student s " +
                "WHERE s.status = :status " +
                "ORDER BY s.lastName")
})
@Entity
@XmlRootElement(name = "Student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends Person implements Serializable {
    private static final long serialVersionUID = -6358383105437539027L;
    @ManyToMany(mappedBy = "students")
    protected List<TutoringSession> sessions;
    protected String school;
    @Digits(integer=2,
            fraction=0,
            message="{invalid.grade.level}")
    protected int grade;
    @ManyToMany
    @XmlTransient
    private List<Guardian> guardians;
    @Enumerated(EnumType.STRING)
    protected StatusType status = StatusType.OUT;
    @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
    @XmlTransient
    protected List<StatusEntry> statusEntries;
    protected boolean active;


    public Student() {
        this.active = true;
        this.sessions = new ArrayList<>();
        this.statusEntries = new ArrayList<>();
        this.guardians = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    /**
     * Get the value of statusEntries
     *
     * @return the value of statusEntries
     */
    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    /**
     * Set the value of statusEntries
     *
     * @param statusEntries new value of statusEntries
     */
    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }


    /**
     * @return the sessions
     */
    public List<TutoringSession> getSessions() {
        return sessions;
    }

    /**
     * @param sessions the sessions to set
     */
    public void setSessions(List<TutoringSession> sessions) {
        this.sessions = sessions;
    }

    /**
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * @return the guardians
     */
    public List<Guardian> getGuardians() {
        return guardians;
    }

    /**
     * @param guardians the guardians to set
     */
    public void setGuardians(List<Guardian> guardians) {
        this.guardians = guardians;
    }
    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getStatusLabel() {
        return this.status.toString();
    }

    /**
     * Get the value of active
     *
     * @return the value of active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the value of active
     *
     * @param active new value of active
     */
    public void setActive(boolean active) {
        this.active = active;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.Student[id=" + id + "]";
    }
}
