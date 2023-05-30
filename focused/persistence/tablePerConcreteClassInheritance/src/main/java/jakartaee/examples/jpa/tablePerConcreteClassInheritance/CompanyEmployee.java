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

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@MappedSuperclass
public abstract class CompanyEmployee extends Employee {

    private Integer vacation;
    @ManyToOne
    private ContractEmployee manager;

    public Integer getVacation() {
        return vacation;
    }

    public void setVacation(Integer vacation) {
        this.vacation = vacation;
    }

    public ContractEmployee getManager() {
        return manager;
    }

    public void setManager(ContractEmployee manager) {
        this.manager = manager;
    }
        
}
