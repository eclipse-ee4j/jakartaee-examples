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
package jakartaee.examples.jaxrs.getclient;

import jakartaee.examples.utils.JakartaEEServer;
import java.io.File;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.arquillian.container.chameleon.runner.ArquillianChameleon;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The JUnit tests for the GET Client example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(ArquillianChameleon.class)
@JakartaEEServer
public class GetClientTest {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Stores the JAX-RS client.
     */
    private Client client;

    /**
     * Setup before testing.
     */
    @Before
    public void before() {
        client = ClientBuilder.newClient();
    }
    
    /**
     * Create the deployment web archive.
     *
     * @return the deployment web archive.
     */
    @Deployment
    public static WebArchive createDeployment() {
        return create(WebArchive.class).
                addClasses(GetClientApplication.class, GetClientResource.class).
                addAsWebResource(new File("src/main/webapp/index.html"));
    }

    /**
     * Tear down after testing.
     */
    @After
    public void after() {
        client.close();
    }

    /**
     * Test issuing a GET request using the Client.
     * 
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient
    @Test
    public void testGet() throws Exception {
        WebTarget target = client.target(baseUrl.toURI());
        String text = target.path("rest/getClient").request().get(String.class);
        assertEquals("And we used @GET", text);
    }
}
