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
package jakartaee.examples.jpa.column;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The JPA class for the @Table example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "jpa_column")
public class ColumnRow implements Serializable {
    
    /**
     * Stores the column.
     */
    @Column(name = "your_column_name")
    private String column;
    
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
    public String getColumn() {
        return column;
    }
    
    /**
     * Set the column.
     * 
     * @param column the column.
     */
    public void setColumn(String column) {
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
