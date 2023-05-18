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

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.MessageDriven;
import jakarta.inject.Named;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

/* This bean asynchronously receives messages from a JMS
 * topic and calls a WebSocket server endpoint */
@Named
@MessageDriven(mappedName = "java:app/traffic-ejb/traffictopic")
public class WebMDB implements MessageListener {

    private static final Logger log = Logger.getLogger("WebSocketMDB");
    
    @Override
    public void onMessage(Message msg) {
        try {
            log.info("[WebMDB] onMessage()");
            String smsg = msg.getBody(String.class);
            log.log(Level.INFO, "[WebMDB] Received: {0}", smsg);
            TrafficEndpoint.sendAll(smsg);
        } catch (JMSException ex) {
            log.log(Level.INFO, "[WebMDB] Exception: {0}", ex.getMessage());
        }
    }

}
