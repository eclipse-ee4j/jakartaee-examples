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
package jakartaee.examples.jpa.column;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * A simple EJB that gives access to the list of rows.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class ColumnEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get the list of rows.
     * 
     * @return the list of rows.
     */
    public List<ColumnRow> getRows() {
        return em.createQuery("SELECT object(r) FROM ColumnRow AS r", ColumnRow.class).getResultList();
    }
}
