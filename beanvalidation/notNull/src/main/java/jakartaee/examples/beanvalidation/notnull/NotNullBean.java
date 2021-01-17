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
package jakartaee.examples.beanvalidation.notnull;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BeanValidation @NotNull example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class NotNullBean {
    
    /**
     * Stores the not null model.
     */
    private NotNullModel notNull = new NotNullModel();

    /**
     * Get the not null model.
     * 
     * @return the not null model.
     */
    public NotNullModel getNotNull() {
        return notNull;
    }
    
    /**
     * Set the not null model.
     * 
     * @param notNull the not null model.
     */
    public void setNotNull(NotNullModel notNull) {
        this.notNull = notNull;
    }
    
    /**
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        return "";
    }
}
