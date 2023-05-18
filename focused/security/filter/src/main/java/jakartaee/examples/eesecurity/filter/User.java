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
package jakartaee.examples.eesecurity.filter;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A User.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Entity
@Table(name = "eesecurity_filter_user")
public class User implements Serializable {

    /**
     * Stores the id.
     */
    @Id
    private BigInteger id;
    
    /**
     * Stores the password.
     */
    @Column(name = "password")
    private String password;
    
    /**
     * Stores the username.
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * Get the id.
     * 
     * @return the id.
     */
    public BigInteger getId() {
        return id;
    }
    
    /**
     * Get the password.
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Get the username.
     * 
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the id.
     * 
     * @param id the id.
     */
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    /**
     * Set the password.
     * 
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Set the username.
     * 
     * @param username the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
