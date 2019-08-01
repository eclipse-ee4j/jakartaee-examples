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

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
