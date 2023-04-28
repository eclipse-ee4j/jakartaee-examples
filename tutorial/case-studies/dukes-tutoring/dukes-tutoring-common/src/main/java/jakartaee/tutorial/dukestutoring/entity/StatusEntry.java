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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.tutorial.dukestutoring.util.StatusType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@XmlRootElement(name = "StatusEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusEntry implements Serializable {
    private static final long serialVersionUID = -4577867285308070101L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusType currentStatus;
    @ManyToOne
    @XmlTransient
    private Student student;
    @ManyToOne
    @XmlTransient
    private TutoringSession tutoringSession;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar statusDate;

    public StatusEntry() {

    }

    public StatusEntry(StatusType status, Student student, TutoringSession session) {
        this.setCurrentStatus(status);
        this.setStudent(student);
        this.setTutoringSession(session);
        this.setStatusDate(Calendar.getInstance());
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
        if (!(object instanceof StatusEntry)) {
            return false;
        }
        StatusEntry other = (StatusEntry) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.StatusEntry[id=" + id + "]";
    }

    /**
     * @return the currentStatus
     */
    public StatusType getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus the currentStatus to set
     */
    public void setCurrentStatus(StatusType currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatusLabel() {
        return currentStatus.toString();
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the tutoringSession
     */
    public TutoringSession getTutoringSession() {
        return tutoringSession;
    }

    /**
     * @param tutoringSession the tutoringSession to set
     */
    public void setTutoringSession(TutoringSession tutoringSession) {
        this.tutoringSession = tutoringSession;
    }

    /**
     * @return the statusDate
     */
    public Calendar getStatusDate() {
        return statusDate;
    }

    /**
     * @param statusDate the statusDate to set
     */
    public void setStatusDate(Calendar statusDate) {
        this.statusDate = statusDate;
    }

    public String getFormattedStatusDate() {
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        return df.format(this.statusDate.getTime());
    }

}
