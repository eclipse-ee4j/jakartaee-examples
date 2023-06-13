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
package jakartaee.examples.focused.security.restformauthcustomatorerememberme;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import jakartaee.examples.utils.ITBase;


/**
 * The integration test for the REST with form authentication, custom store and remember-me example
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class RestFormAuthCustomStoreRememberMeIT extends ITBase {

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
        // Initial request
        HtmlPage loginPage = webClient.getPage(baseUrl + "/rest/resource");
        System.out.println(loginPage.asXml());

        // Response is login form, so we can authenticate
        HtmlForm form = loginPage.getForms()
                                 .get(0);

        form.getInputByName("j_username")
            .setValueAttribute("john");

        form.getInputByName("j_password")
            .setValueAttribute("secret1");

        // After logging in, we should get the actual resource response
        TextPage page = form.getInputByValue("Submit")
                   .click();

        System.out.println(page.getContent());
        
        // Remove all cookies (specially the JSESSONID), except for the
        // JREMEMBERMEID cookie which carries the token to login again
        for (Cookie cookie : webClient.getCookieManager().getCookies()) {
            if (!"JREMEMBERMEID".equals(cookie.getName())) {
                webClient.getCookieManager().removeCookie(cookie);
            }
        }
        
        // Should get the resource response, and not the login form
        TextPage pageAgain = webClient.getPage(baseUrl + "/rest/resource");
        
        System.out.println(pageAgain.getContent());
    }
}
