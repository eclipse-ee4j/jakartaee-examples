/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.jpa.onetomany;

import java.io.Serializable;
import java.util.Set;
import static jakarta.persistence.CascadeType.ALL;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The JPA OneToManyA class for the OneToMany annotation example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_onetomany_a")
public class OneToManyA implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Stores the b's.
     */
    @OneToMany(cascade=ALL, mappedBy="a")
    private Set<OneToManyB> bs;

    /**
     * Get the b's.
     *
     * @return the b's.
     */
    public Set<OneToManyB> getBs() {
        return bs;
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
     * Set the b's.
     * 
     * @param bs the b's.
     */
    public void setBs(Set<OneToManyB> bs) {
        this.bs = bs;
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
