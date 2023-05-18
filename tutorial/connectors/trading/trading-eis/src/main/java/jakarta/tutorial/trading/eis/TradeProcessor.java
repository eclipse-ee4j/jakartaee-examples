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
package jakarta.tutorial.trading.eis;

import java.util.Random;

public class TradeProcessor {
    
    Random random;
    
    public TradeProcessor() {
        random = new Random();
    }
    
    public String getGreeting() {
        return "WELCOME MegaTrade Execution Platform.";
    }
    
    public String getReady() {
        return "READY Accepting trade orders for execution.";
    }
    
    public String processCommand(String command) {
        String ret;
        String[] words = command.split(" ");
        switch(words[0]) {
            case "EXIT":
                ret = "BYE Closing connection.";
                break;
            case "BUY":
            case "SELL":
                int nshares = Integer.parseInt(words[1]);
                String ticker = words[2].toUpperCase();
                String type = words[3].toUpperCase();
                if (type.compareTo("MARKET") == 0) {
                    double price = getPrice(ticker);
                    if (price != -1) {
                        double total = nshares * price;
                        double fee = 0.005 * total;
                        int orderNumber = random.nextInt(10000);
                        ret = String.format("EXECUTED #%d TOTAL %.2f FEE %.2f",
                                            orderNumber, total, fee);
                    } else
                        ret = "ERROR Can't get price for " + ticker;
                } else
                    ret = "ERROR Only MARKET orders supported.";

                break;
            default:
                ret = "ERROR Unknown command.";
        }
        return ret;
    }
    
    /* Return a random price */
    public double getPrice(String t) {
        return 100.0 + 0.01*(random.nextInt(5000) - 2500);
    }
    
}
