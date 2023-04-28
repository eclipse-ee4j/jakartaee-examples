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

import java.util.HashSet;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The EJB for the OneToMany annotation example..
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class OneToManyEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceContext(unitName = "persistenceContextPU")
    private EntityManager em;

    /**
     * Get the OneToManyA.
     * 
     * @return the OneToManyA.
     */
    public OneToManyA getOneToManyA() {
        OneToManyA result = null;
        List<OneToManyA> list = em.createQuery("SELECT object(e) FROM OneToManyA AS e", OneToManyA.class).getResultList();
        if (list.isEmpty()) {
            OneToManyA a = new OneToManyA();
            a.setId(1L);
            HashSet set = new HashSet();
            OneToManyB b = new OneToManyB();
            b.setA(a);
            b.setId(1L);
            set.add(b);
            a.setBs(set);
            em.persist(a);
            list = em.createQuery("SELECT object(e) FROM OneToManyA AS e", OneToManyA.class).getResultList();
        }
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }
}
