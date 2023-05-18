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
package jakarta.tutorial.taskcreator;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/* Notify the clients so that they can refresh the log textarea */
@Dependent
@ServerEndpoint("/wsinfo")
public class InfoEndpoint {

    private static final Logger log = Logger.getLogger("InfoEndpoint");
    /* Keep a list of clients */
    private static final Queue<Session> sessions =
            new ConcurrentLinkedQueue<>();
    
    @OnOpen
    public void onOpen(Session session) {
        log.info("[InfoEndpoint] Connection opened");
        sessions.add(session);
    }
    
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
    
    @OnError
    public void onError(Session session, Throwable t) { }
    
    @OnMessage
    public void onMessage(String msg) { }
    
    /* Observe the event fired from the EJB and notify clients.
     * The clients use JavaScript to make a JSF AJAX request to refresh
     * the log textarea. */
    public static void pushAlert(@Observes String event) {
        for (Session s : sessions) {
            if (s.isOpen())
                try {
                    s.getBasicRemote().sendText(event);
                    log.info("[InfoEndpoint] Event sent");
                } catch (IOException ex) { }
        }
    }
}
