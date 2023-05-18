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
package jakartaee.examples.jpa.persistenceUnit;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

/**
 * A simple EJB that gives access to the list of PersistenceUnitRow instances.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless
public class PersistenceUnitEjb {

    /**
     * Stores the persistence context.
     */
    @PersistenceUnit(unitName = "persistenceUnitPU")
    private EntityManagerFactory emf;
    
    /**
     * Get the list of PersistenceUnitRow instances.
     * 
     * <p>
     *  Note while this code works, it is not recommended that you manage the
     *  lifecycle of your EntityManager yourself. Use PersistenceContext in 
     *  your web application so you do not have to manage the EntityManager
     *  lifecycle yourself.
     * </p>
     * 
     * @return the list of PersistenceUnitRow instances.
     */
    public List<PersistenceUnitRow> getRows() {
        EntityManager em = emf.createEntityManager();
        List result = em.createQuery("SELECT object(e) FROM PersistenceUnitRow AS e", PersistenceUnitRow.class).getResultList();
        em.close();
        return result;
    }
}
