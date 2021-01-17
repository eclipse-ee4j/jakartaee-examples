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
package jakartaee.examples.jpa.converter;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The EJB used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class ConverterEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Get the converter example.
     * 
     * @return the converter example.
     */
    public ConverterExample getConverterExample() {
        List<ConverterExample> result = em.createQuery(
                "SELECT object(r) FROM ConverterExample AS r", ConverterExample.class).getResultList();
        if (result.isEmpty()) {
            ConverterExample row = new ConverterExample();
            row.setId(1L);
            row.setColumn(new ConverterAttribute(1234L));
            em.persist(row);
            result = em.createQuery(
                "SELECT object(r) FROM ConverterExample AS r", ConverterExample.class).getResultList();
        }
        return result.get(0);
    }
}
