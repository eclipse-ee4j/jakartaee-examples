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

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The EJB for the OneToOne annotation example..
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class OneToOneEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "persistenceContextPU")
    private EntityManager em;

    /**
     * Get the OneToOneA.
     * 
     * @return the OneToOneA.
     */
    public OneToOneA getOneToOneA() {
        OneToOneA result = null;
        List<OneToOneA> list = em.createQuery("SELECT object(e) FROM OneToOneA AS e", OneToOneA.class).getResultList();
        if (list.isEmpty()) {
            OneToOneA a = new OneToOneA();
            a.setId(1L);
            OneToOneB b = new OneToOneB();
            b.setId(1L);
            a.setB(b);
            em.persist(a);
            list = em.createQuery("SELECT object(e) FROM OneToOneA AS e", OneToOneA.class).getResultList();
        }
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }
}
