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
package jakarta.tutorial.web.websocketbot;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.inject.Named;

@Named
public class BotBean {
    
    /* Respond to a message from the chat */
    public String respond(String msg) {
        String response;           
        
        /* Remove question marks */
        msg = msg.toLowerCase().replaceAll("\\?", "");
        if (msg.contains("how are you")) {
            response = "I'm doing great, thank you!";
        } else if (msg.contains("how old are you")) {
            Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
            Calendar now = GregorianCalendar.getInstance();
            int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
            response = String.format("I'm %d years old.", dukesAge);
        } else if (msg.contains("when is your birthday")) {
            response = "My birthday is on May 23rd. Thanks for asking!";
        } else if (msg.contains("your favorite color")) {
            response = "My favorite color is blue. What's yours?";
        } else {
            response = "Sorry, I did not understand what you said. ";
            response += "You can ask me how I'm doing today; how old I am; or ";
            response += "what my favorite color is.";
        }
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) { }
        return response;
    }
}
