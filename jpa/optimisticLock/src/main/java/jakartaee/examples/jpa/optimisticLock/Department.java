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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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