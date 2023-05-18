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
package jakartaee.examples.jpa.converter;

/**
 * The attribute used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class ConverterAttribute {
    
    /**
     * Stores the long.
     */
    private Long value;
    
    /**
     * Constructor.
     */
    public ConverterAttribute() {
    }
    
    /**
     * Constructor.
     * 
     * @param value the value.
     */
    public ConverterAttribute(Long value) {
        this.value = value;
    }
    
    /**
     * Get the value.
     * 
     * @return the value.
     */
    public Long getValue() {
        return value;
    }
    
    /**
     * Set the value.
     * 
     * @param value the value.
     */
    public void setValue(Long value) {
        this.value = value;
    }
}
