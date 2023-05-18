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
package jakartaee.examples.faces.requestscoped;

import java.util.Date;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * A request scoped bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "requestScopedBean")
@RequestScoped
public class RequestScopedBean {

    /**
     * Get the time.
     * 
     * @return the time.
     */
    public String getTime() {
        return new Date().toString() + " - " + System.nanoTime();
    }
}
