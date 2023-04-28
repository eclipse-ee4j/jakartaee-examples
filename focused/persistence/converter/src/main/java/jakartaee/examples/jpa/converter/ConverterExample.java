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
package jakartaee.examples.jpa.converter;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The JPA class used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_converter")
public class ConverterExample implements Serializable {
    
    /**
     * Stores the column.
     */
    @Convert(converter = ConverterConverter.class)
    @Column(name = "your_column_name")
    private ConverterAttribute column;
    
    /**
     * Stores the id.
     */
    @Id
    private Long id;

    /**
     * Get the id.
     * 
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Get the column.
     * 
     * @return the column.
     */
    public ConverterAttribute getColumn() {
        return column;
    }
    
    /**
     * Set the column.
     * 
     * @param column the column.
     */
    public void setColumn(ConverterAttribute column) {
        this.column = column;
    }

    /**
     * Set the id.
     * 
     * @param id the id.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
