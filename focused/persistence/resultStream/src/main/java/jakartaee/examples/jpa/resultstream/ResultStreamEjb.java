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
package jakartaee.examples.jpa.resultstream;

import java.util.List;
import java.util.stream.Stream;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The EJB for the createQuery example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class ResultStreamEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get all the rows.
     * 
     * @return all the rows.
     */
    public Stream<ResultStreamRow> getRows() {
         List<ResultStreamRow> list = em.createQuery(
                "SELECT object(r) FROM ResultStreamRow AS r", ResultStreamRow.class).getResultList();
        if (list.isEmpty()) {
            ResultStreamRow row = new ResultStreamRow();
            row.setId(1L);
            em.persist(row);
            row = new ResultStreamRow();
            row.setId(2L);
            em.persist(row);
        }
        return em.createQuery("SELECT object(r) FROM ResultStreamRow AS r", ResultStreamRow.class).getResultStream();
    }
}
