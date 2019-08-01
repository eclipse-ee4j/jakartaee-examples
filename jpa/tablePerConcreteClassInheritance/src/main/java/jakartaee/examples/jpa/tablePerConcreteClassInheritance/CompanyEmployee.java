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

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@MappedSuperclass
public abstract class CompanyEmployee extends Employee {

    private Integer vacation;
    @ManyToOne
    private ContractEmployee manager;

    public Integer getVacation() {
        return vacation;
    }

    public void setVacation(Integer vacation) {
        this.vacation = vacation;
    }

    public ContractEmployee getManager() {
        return manager;
    }

    public void setManager(ContractEmployee manager) {
        this.manager = manager;
    }
        
}
