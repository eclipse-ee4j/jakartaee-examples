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
package jakartaee.examples.jpa.onetoone;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The JPA OneToOneA class for the OneToOne annotation example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "onetoone_a")
public class OneToOneA implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Stores the b.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private OneToOneB b;

    /**
     * Get the b.
     *
     * @return the b.
     */
    public OneToOneB getB() {
        return b;
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
     * Set the b.
     * 
     * @param b the b.
     */
    public void setB(OneToOneB b) {
        this.b = b;
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
