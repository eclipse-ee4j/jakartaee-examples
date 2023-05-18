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
package jakartaee.examples.beanvalidation.nullannotation;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the BenValidation @Null example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class NullBean {
    
    /**
     * Stores the null model.
     */
    private NullModel nullModel = new NullModel();

    /**
     * Get the null model.
     * 
     * @return the null model.
     */
    public NullModel getNullModel() {
        return nullModel;
    }
    
    /**
     * Set the null model.
     * 
     * @param nullModel the null model.
     */
    public void setNullModel(NullModel nullModel) {
        this.nullModel = nullModel;
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
