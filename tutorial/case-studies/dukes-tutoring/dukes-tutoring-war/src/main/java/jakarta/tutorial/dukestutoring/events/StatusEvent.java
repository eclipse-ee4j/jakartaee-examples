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
package jakarta.tutorial.dukestutoring.events;

import java.io.Serializable;

import jakartaee.tutorial.dukestutoring.entity.Student;

/**
 *
 * @author ievans
 */
public class StatusEvent implements Serializable {
    
    private Student student;

    public StatusEvent() {
    }
    
    public StatusEvent(Student s) {
        student = s;
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
    
}
