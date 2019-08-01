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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Seyed Mohammad Hossein Jamali (smhjamali@yahoo.com)
 */
@Entity
@Table(name = "CONTRACT_EMPLOYEE")
@AttributeOverrides({
    @AttributeOverride(name = "name", column=@Column(name = "FULLNAME")),
    @AttributeOverride(name = "startDate", column=@Column(name = "SDATE"))    
})
public class ContractEmployee extends Employee {

    @Column(name = "DAILY_RATE")
    private Integer dailyRate;
    private Integer term;

    public Integer getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Integer dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
        
}
