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
package jakartaee.examples.servlet.servletrequestlistener;

import java.util.Date;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

/**
 * The ServletRequestListener for the Servlet API ServletRequestListener example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example ServletRequestListener")
public class ServletRequestListenerExample implements ServletRequestListener {

    /**
     * A Servlet request is destroyed.
     * 
     * @param event the event. 
     */
    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println("Request was desttroyed at: " + new Date());
        long startTime = (long) event.getServletRequest().getAttribute("startTime");
        System.out.println("Request took " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
    
    /**
     * A Servlet request is initialized.
     * 
     * @param event the event 
     */
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        System.out.println("Request was initialized at: " + new Date());
        event.getServletRequest().setAttribute("startTime", System.currentTimeMillis());
        event.getServletRequest().setAttribute("requestInitializedCalled", "true");
    }
}
