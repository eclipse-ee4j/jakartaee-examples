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

import java.util.List;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Inheritance with Table-per-Concrete-Class strategy
 * 
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Named("employeeBean")
@RequestScoped
public class EmployeeBean {

    private List<ContractEmployee> contractEmps;
    private List<FullTimeEmployee> fullTimeEmps;
    private List<PartTimeEmployee> partTimeEmps;
    
    /**
     * Stores the EJB.
     */    
    @EJB
    private EmployeeEjb employeeEjb;
    
    /**
     * Saves three employees of type ContractEmployee, FullTimeEmployee and PartTimeEmployee
     */
    public void saveAndLoad() {
        employeeEjb.persist();

        contractEmps = employeeEjb.findAllContractEmployees();
        fullTimeEmps = employeeEjb.findAllFullTimeEmployees();
        partTimeEmps = employeeEjb.findAllPartTimeEmployees();
    }    

    public List<ContractEmployee> getContractEmps() {
        return contractEmps;
    }

    public List<FullTimeEmployee> getFullTimeEmps() {
        return fullTimeEmps;
    }

    public List<PartTimeEmployee> getPartTimeEmps() {
        return partTimeEmps;
    }        
    
}
