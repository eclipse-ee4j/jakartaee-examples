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
package jakartaee.examples.jpa.tablePerConcreteClassInheritance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Entity
@Table(name = "FULLTIME_EMPLOYEE")
public class FullTimeEmployee extends CompanyEmployee {

    private Long salary;
    @Column(name = "PENSION")
    private Long pensionContribution;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getPensionContribution() {
        return pensionContribution;
    }

    public void setPensionContribution(Long pensionContribution) {
        this.pensionContribution = pensionContribution;
    }       
    
}
