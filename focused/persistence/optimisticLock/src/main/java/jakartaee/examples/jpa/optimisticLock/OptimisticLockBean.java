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
package jakartaee.examples.jpa.optimisticLock;

import java.io.Serializable;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Named
@SessionScoped
public class OptimisticLockBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(OptimisticLockBean.class.getName());
    
    @EJB
    private OptimisticLockEjb optimisticLockEjb;
        
    private Long departmentsCount = -1L;
    private Long employeesCount = -1L;
    private Long uniformsCount = -1L;
    private Long uniformscountOfDeptX = 0L;
    private Long employeeNumber9UniformsCount = 0L;
    
    @PostConstruct
    private void init(){        
        LOG.info("Initializing database...");
        optimisticLockEjb.initDepartments();
        LOG.info("Initializing couter fields...");
        this.departmentsCount = optimisticLockEjb.findDepartmentCount();
        this.employeesCount = optimisticLockEjb.findEmployeeCount();
        this.uniformsCount = optimisticLockEjb.findUniformCount();
    }
    
    public void refreshCounterFields(){
        this.departmentsCount = optimisticLockEjb.findDepartmentCount();
        this.employeesCount = optimisticLockEjb.findEmployeeCount();
        this.uniformsCount = optimisticLockEjb.findUniformCount();        
    }
    
    /**
     * Finds a department with an optimistic read lock.
     * Departments have already been initialized in another bean called OptimisticLockDbInitializerBean.
     */
    public void findCountOfUniforms(){
        Long result = optimisticLockEjb.findCountOfUniformsInADepartmentWithOptimisticLock("_X");
        LOG.info("Count : " + result); 
        this.uniformscountOfDeptX = result;
    }
    
   /**
    * Gives one uniform to employee #9 of department X .
    */
    public void giveUniform(){
        optimisticLockEjb.giveUniforms("9", 1, "_X");
        this.employeeNumber9UniformsCount = optimisticLockEjb.findUniformCountByCode("9");
    }

    public Long getUniformsCount() {
        return uniformsCount;
    }              

    public Long getDepartmentsCount() {
        return departmentsCount;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

	public Long getUniformscountOfDeptX() {
		return uniformscountOfDeptX;
	}

	public Long getEmployeeNumber9UniformsCount() {
		return employeeNumber9UniformsCount;
	}    
    
}
