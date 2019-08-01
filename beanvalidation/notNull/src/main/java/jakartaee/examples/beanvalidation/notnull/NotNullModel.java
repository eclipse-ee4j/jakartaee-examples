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

import javax.validation.constraints.NotNull;

/**
 * The model for the BeanValidation @NotNull example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class NotNullModel {
    
    /**
     * Stores the string.
     */
    @NotNull(message = "This cannot be null")
    private String string;
    
    /**
     * Get the string.
     * 
     * @return the string.
     */
    public String getString() {
        return string;
    }
    
    /**
     * Set the string.
     * 
     * @param string the string.
     */
    public void setString(String string) {
        this.string = string;
    }
}
