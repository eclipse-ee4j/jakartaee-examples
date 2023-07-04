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
package jakartaee.examples.focused.security.restopenidconnectauth;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;


/**
 * The integration test for the REST with OpenID Connect authentication example
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class RestOpenIdConnectAuthIT extends ITBase {

    @ArquillianResource
    private URL baseUrl;

    /**
     * Test the call to a protected REST service
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient
    @Test
    public void testRestCall() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl + "/rest/resource");

        // Authenticate with the OpenId Provider using the username and password for a default user
        page.getElementById("j_username")
            .setAttribute("value", "user");

        page.getElementById("j_password")
            .setAttribute("value", "password");

        // Submit login form
        HtmlPage confirmationPage = page.getElementByName("submit")
                                        .click();

        System.out.println(confirmationPage.asXml());

        // Confirm
        TextPage originalResource = confirmationPage.getElementByName("authorize")
                                                    .click();

        System.out.println(originalResource.getContent());
    }
}
