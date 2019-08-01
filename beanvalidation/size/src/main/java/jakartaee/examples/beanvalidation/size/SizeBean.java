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
package jakartaee.examples.beanvalidation.size;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BeanValidation @Size example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class SizeBean {
    
    /**
     * Stores the size.
     */
    private SizeModel size = new SizeModel();

    /**
     * Get the size.
     * 
     * @return the text.
     */
    public SizeModel getSize() {
        return size;
    }
    
    /**
     * Set the size.
     * 
     * @param size the size.
     */
    public void setSize(SizeModel size) {
        this.size = size;
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
