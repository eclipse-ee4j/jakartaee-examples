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

import java.util.Date;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that persist employees information in database
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Stateless
public class EmployeeEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "inheritancePU")
    private EntityManager em;    
    
    /**
     * Persist three employees of types ContractEmployee, FullTimeEmployee and PartTimeEmployee in database
     */
    public void persist() {

        ContractEmployee contractEmp = new ContractEmployee();
        contractEmp.setName("Sohrab");
        contractEmp.setDailyRate(Integer.MAX_VALUE);
        contractEmp.setTerm(10);
        contractEmp.setStartDate(new Date());
        em.persist(contractEmp);
        
        FullTimeEmployee fullTimeEmp = new FullTimeEmployee();
        fullTimeEmp.setManager(contractEmp);
        fullTimeEmp.setPensionContribution(Long.MIN_VALUE);
        fullTimeEmp.setSalary(Long.MAX_VALUE);
        fullTimeEmp.setName("Reza");
        fullTimeEmp.setStartDate(new Date());
        fullTimeEmp.setVacation(2);
        
        PartTimeEmployee partTimeEmp = new PartTimeEmployee();
        partTimeEmp.setHourlyRate(10.0F);
        partTimeEmp.setManager(contractEmp);
        partTimeEmp.setName("Nelson");
        partTimeEmp.setStartDate(new Date());
        partTimeEmp.setVacation(5);
        
        em.persist(fullTimeEmp);
        em.persist(partTimeEmp);

    }    
    
    public List<ContractEmployee> findAllContractEmployees(){
        return em.createQuery("FROM ContractEmployee c").getResultList();
    }
    
    public List<FullTimeEmployee> findAllFullTimeEmployees(){
        return em.createQuery("FROM FullTimeEmployee f").getResultList();
    }
    
    public List<PartTimeEmployee> findAllPartTimeEmployees(){
        return em.createQuery("FROM PartTimeEmployee p").getResultList();
    }
    
}
