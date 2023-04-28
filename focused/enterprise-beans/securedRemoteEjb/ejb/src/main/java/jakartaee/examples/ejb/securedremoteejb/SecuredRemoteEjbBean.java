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
package jakartaee.examples.ejb.securedremoteejb;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;

/**
 * A secured remote stateless session bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Stateless(name = "securedRemoteEjb")
public class SecuredRemoteEjbBean implements SecuredRemoteEjb {

    /**
     * Hello world!
     * 
     * @return "Hello World!"
     */
    @Override
    @RolesAllowed(value = {"user"})
    public String helloWorld() {
        return "Hello World!";
    }    
}
