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
package jakartaee.examples.jpa.persistenceContext;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @Entity.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("persistenceContextBean")
@RequestScoped
public class PersistenceContextBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private PersistenceContextEjb ejb;
    
    /**
     * Get the rows (instances of PersistenceContextRow).
     * 
     * @return the rows.
     */
    public List<PersistenceContextRow> getRows() {
        return ejb.getRows();
    }
}
