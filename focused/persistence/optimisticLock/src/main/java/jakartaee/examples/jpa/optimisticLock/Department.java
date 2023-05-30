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

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Department.findByName",
            query = "SELECT d FROM Department d WHERE d.name = :name",
            lockMode = LockModeType.OPTIMISTIC
    ),
    @NamedQuery(
            name = "Department.findDepartmentCount",
            query = "SELECT COUNT(d) FROM Department d"
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Department.findCountOfUniforms",
            query = "SELECT SUM(employeeInfo.cnt) FROM ("
            + "SELECT COUNT(uniform.id) AS cnt, employee.id "
            + "FROM department d "
            + "JOIN empoyee emp on emp.department_id = d.id "
            + "JOIN uniform un on un.employee_id = emp.id "
            + "WHERE d.name = :name GROUP BY emp.id) AS employeeInfo "
    )
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
    
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employees;

    @Version
    private Long version;
    
    public Department(String name) {
        this.name = name;
    }    

    public Department() {
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> empoyees) {
        this.employees = empoyees;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }        
    
}