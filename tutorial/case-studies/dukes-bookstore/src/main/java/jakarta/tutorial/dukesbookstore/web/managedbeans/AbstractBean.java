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
package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * <p>Abstract base class for managed beans to share utility methods.</p>
 */
@Named
@SessionScoped
public class AbstractBean implements Serializable {

    private static final long serialVersionUID = -3375564172975657665L;
    @Inject
    ShoppingCart cart;

    /**
     * @return the <code>FacesContext</code> instance for the current request.
     */
    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }

    /**
     * <p>Add a localized message to the
     * <code>FacesContext</code> for the current request.</p>
     *
     * @param clientId Client identifier of the component this message relates
     * to, or <code>null</code> for global messages
     * @param key Message key of the message to include
     */
    protected void message(String clientId, String key) {
        // Look up the requested message text
        String text;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                    "jakarta.tutorial.dukesbookstore.web.messages.Messages",
                    context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(clientId, new FacesMessage(text));
    }

    /**
     * <p>Add a localized message to the
     * <code>FacesContext</code> for the current request.</p>
     *
     * @param clientId Client identifier of the component this message relates
     * to, or <code>null</code> for global messages
     * @param key Message key of the message to include
     * @param params Substitution parameters for using the localized text as a
     * message format
     */
    protected void message(String clientId, String key, Object[] params) {
        // Look up the requested message text
        String text;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                    "jakarta.tutorial.dukesbookstore.web.messages.Messages",
                    context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Perform the requested substitutions
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        // Construct and add a FacesMessage containing it
        context().addMessage(clientId, new FacesMessage(text));
    }
}
