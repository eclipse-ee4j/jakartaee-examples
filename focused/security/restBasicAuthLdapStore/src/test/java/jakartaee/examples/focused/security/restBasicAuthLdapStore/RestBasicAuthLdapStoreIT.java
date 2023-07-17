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
package jakartaee.examples.focused.security.restBasicAuthLdapStore;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.TextPage;

import jakartaee.examples.utils.ITBase;


/**
 * The integration test for the REST with basic authentication and LDAP example
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class RestBasicAuthLdapStoreIT extends ITBase {

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
        DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
        credentialsProvider.addCredentials("john", "secret1");

        webClient.setCredentialsProvider(credentialsProvider);

        TextPage page = webClient.getPage(baseUrl + "/rest/resource");
        String content = page.getContent();

        System.out.println(content);
    }
}
