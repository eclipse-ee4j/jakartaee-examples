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
package jakarta.tutorial.traffic.war;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/* This endpoint forwards to web clients the JSON messages 
 * received by the WebMDB bean from the JMS topic */
@ServerEndpoint("/wstraffic")
public class TrafficEndpoint {
    
    /* Queue for all open WebSocket sessions */
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    
    private static final Logger log = Logger.getLogger("TrafficEndpoint");
    
    /* Called by WebMDB when it receives messages from the JMS topic */
    public static synchronized void sendAll(String msg) {
        log.info("[TrafficEndpoint] sendAll()");
        try {
            /* Send messages from the JMS queue to all sessions */
            for (Session session : queue) {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(msg);
                    log.log(Level.INFO, "[TrafficEndpoint] Sent: {0}", msg);
                }
            }
        } catch (IOException e) {
            log.log(Level.INFO, "[TrafficEndpoint] Exception: {0}", e.getMessage());
        }
    }
    
    /* Add and remove sesions from the queue */
    @OnOpen
    public void openConnection(Session session) {
        log.info("[TrafficEndpoint] openConnection()");
        queue.add(session);
    }
    
    @OnClose
    public void closedConnection(Session session) {
        log.info("[TrafficEndpoint] closedConnection()");
        queue.remove(session);
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        log.info("[TrafficEndpoint] error()");
    }
}
