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
package jakartaee.examples.jpa.id;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * The JPA class for the @Id example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
public class IdExample implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private BigInteger id;

    /**
     * Get the id.
     * 
     * @return the id.
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Set the id.
     * 
     * @param id the id.
     */
    public void setId(BigInteger id) {
        this.id = id;
    }
}
