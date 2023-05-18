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
package jakartaee.examples.servlet.httpsessionlistener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * An example HttpSessionListener.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example HttpSessionListener")
public class HttpSessionListenerExample implements HttpSessionListener {

    /**
     * Handles the session created event.
     * 
     * @param event the HTTP session event. 
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session " + event.getSession().getId() + " was created");
    }

    /**
     * Handles the session destroyed event.
     * 
     * @param event the HTTP session event. 
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session " + event.getSession().getId() + " was destroyed");
    }
}
