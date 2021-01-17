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
package jakartaee.examples.websocket.onclose;

import java.io.IOException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/**
 * The ServerEndpoint for the @OnClose example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ServerEndpoint("/echo")
public class OnCloseEndpoint {

    /**
     * Handle the text message.
     *
     * @param session the session.
     * @param message the message.
     * @throws IOException when an I/O error occurs.
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
        session.close();
    }

    /**
     * Handle to onOpen.
     *
     * @param session the session.
     * @throws IOException when an I/O error occurs.
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("Opening connection");
    }
}
