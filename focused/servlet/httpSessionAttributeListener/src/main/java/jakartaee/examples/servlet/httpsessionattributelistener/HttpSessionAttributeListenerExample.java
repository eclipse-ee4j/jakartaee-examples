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
package jakartaee.examples.servlet.httpsessionattributelistener;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * An example HttpSessionAttributeListener.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebListener(value = "A example HttpSessionAttributeListener")
public class HttpSessionAttributeListenerExample implements HttpSessionAttributeListener {
    
    /**
     * Stores the servlet context.
     */
    @Inject
    ServletContext servletContext;

    /**
     * Handle the attribute added event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeAdded", event.getName());
        servletContext.setAttribute("attributeAddedValue", event.getValue());
    }

    /**
     * Handle the attribute removed event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeRemoved", event.getName());
    }

    /**
     * Handle the attribute replaced event.
     * 
     * @param event the event.
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        servletContext.setAttribute("attributeReplaced", event.getName());
        servletContext.setAttribute("attributeReplacedValue", event.getValue());
    }
}
