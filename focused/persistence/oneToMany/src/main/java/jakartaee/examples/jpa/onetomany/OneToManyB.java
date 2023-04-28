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
package jakartaee.examples.jpa.onetomany;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The JPA OneToManyB class for the OneToMany annotation example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_onetomany_b")
public class OneToManyB implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Get the a.
     *
     * @return the a.
     */
    @ManyToOne
    @JoinColumn(name = "a", nullable = false)
    private OneToManyA a;
    
    /**
     * Get the A.
     * 
     * @return the a.
     */
    public OneToManyA getA() {
        return a;
    }

    /**
     * Get the id.
     *
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the A.
     * 
     * @param a the A.
     */
    public void setA(OneToManyA a) {
        this.a = a ;
    }

    /**
     * Set the id.
     *
     * @param id the id.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
