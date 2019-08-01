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

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Stateless
public class OptimisticLockEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "optimisticLockPU")
    private EntityManager em;

    /**
     * Find a department by its name
     *
     * @param name Name of a department
     * @return A department with specified name
     */
    public Department findDepartmentByName(String name) {
        return (Department) em.createNamedQuery("Department.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

    /**
     * Find an employee by her code
     *
     * @param code Employee's code
     * @return An employee whose code is specified
     */
    public Employee findEmployeeByCode(String code, String departmentName) {
        return (Employee) em.createNamedQuery("Employee.findByCode")
                .setParameter("code", code)
                .setParameter("departmentName", departmentName)
                .getSingleResult();
    }

    /**
     * Initializes two departments
     */
    public void initDepartments() {
        Department depx = new Department("_X");
        Department depy = new Department("_Y");
        em.persist(depx);
        em.persist(depy);
        
        createNewEmployee("Bahman", "1", depx);
        createNewEmployee("Bahram", "9", depx);
        createNewEmployee("Cyrus", "7", depx);

        createNewEmployee("Alireza", "9", depy);
        createNewEmployee("Mohammad", "2", depy);
        createNewEmployee("Hossein", "11", depy);

        giveUniforms("1", 1, "_X");
        giveUniforms("2", 3, "_Y");
        giveUniforms("11", 5, "_Y");
        giveUniforms("7", 7, "_X");
    }

    /**
     * Creates an employee and assign her to a department
     *
     * @param name Employee's name
     * @param code Employee's code
     * @param dep Employee's department
     */
    public void createNewEmployee(String name, String code, Department dep) {
        Employee employee = new Employee(name, code, dep);
        em.persist(employee);
    }    
    
    public Long findCountOfUniformsInADepartmentWithNoLock(String departmentName) {
        Query query = em.createNamedQuery("Department.findCountOfUniforms")
                .setParameter("name", departmentName);

        return (Long) query.getSingleResult();
    }

    /**
     * Give a uniform to specified employee
     *
     * @param employeeCode Employee's code
     * @param count Count of uniforms an employee need
     */
    public void giveUniforms(String employeeCode, Integer count, String departmentName) throws RuntimeException {
        System.out.println("Giving " + count + " uniforms to emp #" + employeeCode);
        try {
            Employee emp = findEmployeeByCode(employeeCode, departmentName);
            if (emp.getUniforms() == null) {
                emp.setUniforms(new ArrayList<Uniform>());
            }
            for (int i = 0; i < count; i++) {
                Uniform uniform = new Uniform(emp);
                em.persist(uniform);
                emp.getUniforms().add(uniform);
            }
        } catch (OptimisticLockException e) {
            throw new RuntimeException("an-error-occured-please-try-again", e);
        }
    }

    /**
     * Find total number of uniforms which is given to an employee
     * @param employeeCode
     * @return Total number of uniforms which is given to an employee 
     */
    public Long findUniformCountByCode(String employeeCode) {
    	return (Long) em.createNamedQuery("Employee.findUniformCountByCode")
    			.setParameter("code", employeeCode)
    			.setParameter("departmentName", "_X")
    			.getSingleResult();
    }
    
    /**
     * Find total number of uniforms have been given to employees.
     * @param departmentName Name of department for which finding total number of assigned uniforms. 
     * @return Total number of uniforms have been given to employees of department
     * @throws RuntimeException 
     */
    public Long findCountOfUniformsInADepartmentWithOptimisticLock(String departmentName) throws RuntimeException {
        try {
            Query query = em.createNamedQuery("Department.findByName")
                    .setParameter("name", departmentName.trim())
                    .setLockMode(LockModeType.OPTIMISTIC);
                                
            Department department = (Department) query.getSingleResult();
             
            Long count = 0L;

            for (Employee emp : department.getEmployees()) {
                
                em.lock(emp, LockModeType.OPTIMISTIC);
                               
                // We can use find and refresh methods as well
                //em.find(Employee.class, 1L, LockModeType.OPTIMISTIC);
                //em.refresh(emp, LockModeType.OPTIMISTIC);
                
                // Create a delay intentionally to keep the optimistic lock a bit longer
                System.out.println("Locking employee #" + emp.getCode() + " with " + emp.getUniforms().size() + " uniforms");
                if(emp.getCode().equals("9")) {
                	Thread.sleep(15000L);
                }                
                count += emp.getUniforms().size();
            }

            return count;
        } catch (InterruptedException ex) {
            throw new RuntimeException("an-error-occured-please-try-again", ex);
        }
    }
    
    public Long findDepartmentCount(){
        return (Long) em.createNamedQuery("Department.findDepartmentCount")
                .getSingleResult();
    }
    
    public Long findEmployeeCount(){
        return (Long) em.createNamedQuery("Employee.findEmployeeCount")
                .getSingleResult();        
    }

    public Long findUniformCount() {
        return (Long) em.createNamedQuery("Uniform.findUniformCount")
                .getSingleResult();
    }
    
}
