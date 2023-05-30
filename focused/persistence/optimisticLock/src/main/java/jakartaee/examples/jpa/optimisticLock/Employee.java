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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
            name = "Employee.findByCode",
            query = "SELECT e FROM Employee e WHERE e.code = :code AND e.department.name = :departmentName"
    ),
    @NamedQuery(
            name = "Employee.findUniformCountByCode",
            query = "SELECT COUNT(u) FROM Employee e JOIN e.uniforms u WHERE e.code = :code AND e.department.name = :departmentName"
    ),    
    @NamedQuery(
            name = "Employee.findEmployeeCount",
            query = "SELECT COUNT(e) FROM Employee e"
    )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(name = "CODE")
    private String code;
    
    @OneToMany(mappedBy = "employee")
    private List<Uniform> uniforms;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Version
    private Long version;    
    
    public Employee(String name) {
        this.name = name;
    }

    public Employee() {
    }
    
    public Employee(String name, String code, Department department) {
        this.name = name;
        this.code = code;
        this.department = department;
    }    
    
    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
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

    public List<Uniform> getUniforms() {
        return uniforms;
    }

    public void setUniforms(List<Uniform> uniforms) {
        this.uniforms = uniforms;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }        
    
}
