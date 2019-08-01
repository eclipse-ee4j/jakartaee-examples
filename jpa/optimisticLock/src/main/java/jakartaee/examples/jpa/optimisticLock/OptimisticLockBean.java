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

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
