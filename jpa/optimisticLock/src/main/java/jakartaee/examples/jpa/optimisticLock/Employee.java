/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.jpa.optimisticLock;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

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
