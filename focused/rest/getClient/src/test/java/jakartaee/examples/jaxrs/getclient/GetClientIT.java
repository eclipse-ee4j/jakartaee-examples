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


import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the GET Client example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class GetClientIT extends ITBase {

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
