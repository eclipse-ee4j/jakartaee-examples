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

package jakartaee.examples.focused.security.restCustomFormAuthCustomStore;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The Login backing is the bean that backs the login.xhtml page.
 *
 * <p>
 * This is essentially an extension to the provided (custom) form authentication mechanism, handling the
 * view presentation and the input validation.
 */
@Named
@RequestScoped
public class LoginBacking {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    @NotNull
    @Size(min = 3, max = 15, message="Username must be between 3 and 15 characters")
    private String username;

    @NotNull
    @Size(min = 5, max = 50, message="Password must be between 5 and 50 characters")
    private String password;

    public void login() {
        switch (
            // Continue the authentication dialog manually by invoking the authenticate()
            // method. The form authentication picks this up, just like a post to j_security does.
            securityContext.authenticate(
                getRequest(),
                getResponse(),
                withParams()
                    .credential(new UsernamePasswordCredential(username, new Password(password))))) {

            case SEND_CONTINUE:

                // Authentication mechanism has send a redirect, should not
                // send anything to response from Face now.
                facesContext.responseComplete();
                return;

            case SEND_FAILURE:

                addError("Login failed");
                return;

            default:
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) facesContext
            .getExternalContext()
            .getResponse();
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) facesContext
            .getExternalContext()
            .getRequest();
    }

    private void addError(String message) {
        facesContext
            .addMessage(
                null,
                new FacesMessage(SEVERITY_ERROR, message, null));
    }

}
