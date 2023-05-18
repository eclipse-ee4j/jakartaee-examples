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

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * The bean used to demonstrate usage of @PersistenceUnit.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named("persistenceUnitBean")
@RequestScoped
public class PersistenceUnitBean implements Serializable {
    
    /**
     * Stores the EJB.
     */
    @Inject
    private PersistenceUnitEjb ejb;
    
    /**
     * Get the rows (instances of PersistenceUnitRow).
     * 
     * @return the rows.
     */
    public List<PersistenceUnitRow> getRows() {
        return ejb.getRows();
    }
}
