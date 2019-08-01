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
package jakartaee.examples.jpa.tablePerConcreteClassInheritance;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
