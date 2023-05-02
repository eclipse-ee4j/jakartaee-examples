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
package jakartaee.examples.websocket.onerror;


import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.glassfish.tyrus.client.ClientManager;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakartaee.examples.utils.ITBase;

/**
 * A JUnit test for the @OnError example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ClientEndpoint
@RunWith(Arquillian.class)
public class OnErrorEndpointIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Stores our buffer.
     */
    private final StringBuilder buffer = new StringBuilder();

    /**
     * Stores our countdown latch.
     */
    private CountDownLatch countDown = new CountDownLatch(1);

    /**
     * Get the buffer.
     *
     * @return the buffer.
     */
    public String getBuffer() {
        return buffer.toString();
    }

    /**
     * Handle the OnError.
     *
     * @param cause the cause.
     */
    @OnError
    public void onError(Throwable cause) {
        buffer.append(cause.getMessage());
        countDown.countDown();
    }

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param message the message.
     * @throws Exception when a serious error occurs.
     */
    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        session.close();
        session.getBasicRemote().sendText(message);
    }

    /**
     * Test the client endpoint.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    @RunAsClient
    public void testClientEndpoint() throws Exception {
        countDown = new CountDownLatch(1);
        ClientManager client = ClientManager.createClient();
        StringBuilder wsUrl = new StringBuilder();
        wsUrl.append("ws://").append(baseUrl.getHost()).append(":").
                append(baseUrl.getPort()).append(baseUrl.getPath()).append("echo");
        client.connectToServer(this, new URI(wsUrl.toString()));
        countDown.await(100, TimeUnit.SECONDS);
        System.out.println(buffer.toString());
        assertTrue(buffer.toString().contains("The connection has been closed."));
    }
}
