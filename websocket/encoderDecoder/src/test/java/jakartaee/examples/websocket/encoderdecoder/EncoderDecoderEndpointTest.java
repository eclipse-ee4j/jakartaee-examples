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
package jakartaee.examples.websocket.encoderdecoder;

import jakartaee.examples.utils.JakartaEEServer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.websocket.ClientEndpoint;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.arquillian.container.chameleon.runner.ArquillianChameleon;
import org.glassfish.tyrus.client.ClientManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * An annotated ClientEndpoint for the annotated ClientEndpoint example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ClientEndpoint(
        encoders = {EncoderDecoderEncoder.class},
        decoders = {EncoderDecoderDecoder.class}
)
@RunWith(ArquillianChameleon.class)
@JakartaEEServer
public class EncoderDecoderEndpointTest {

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
     * Create the deployment web archive.
     *
     * @return the deployment web archive.
     */
    @Deployment
    public static WebArchive createDeployment() {
        return create(WebArchive.class).addClasses(
                EncoderDecoderEndpoint.class, EncoderDecoder.class,
                EncoderDecoderDecoder.class, EncoderDecoderEncoder.class).
                addAsWebResource(new File("src/main/webapp/index.xhtml")).
                addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    /**
     * Get the buffer.
     *
     * @return the buffer.
     */
    public String getBuffer() {
        return buffer.toString();
    }

    /**
     * Handle the on open event.
     *
     * @param session the session.
     */
    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendObject(new EncoderDecoder("ECHO"));
        } catch (IOException | EncodeException e) {
            buffer.append(e.getMessage()); 
        }
    }

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param encoderDecoder the message.
     */
    @OnMessage
    public void onMessage(Session session, EncoderDecoder encoderDecoder) {
        buffer.append(encoderDecoder.toString());
        countDown.countDown();
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
        assertEquals("ECHO", buffer.toString());
    }
}
