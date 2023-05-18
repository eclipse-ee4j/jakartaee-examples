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
package jakarta.tutorial.trading.rar.api;

/* Represents a trade order for the EIS */
public class TradeOrder {

    public enum OrderType { BUY, SELL };
    public enum OrderClass { MARKET };
    public enum Ticker { WWWW, YYYY, ZZZZ };
    
    private OrderType orderType;
    private int nShares;
    private Ticker ticker;
    private OrderClass orderClass;
    
    public TradeOrder() {
        orderType = OrderType.BUY;
        nShares = 100;
        ticker = Ticker.YYYY;
        orderClass = OrderClass.MARKET;
    }
    
    @Override
    public String toString() {
        return String.format("%s %d %s %s", orderType.toString(),
                                            nShares, ticker,
                                            orderClass.toString());
    }
    
    /* Getters and setters */
    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }
    public int getNShares() { return nShares; }
    public void setNShares(int nShares) { this.nShares = nShares; }
    public Ticker getTicker() { return ticker; }
    public void setTicker(Ticker ticker) { this.ticker = ticker; }
    public OrderClass getOrderClass() { return orderClass; }
    public void setOrderClass(OrderClass orderClass) { this.orderClass = orderClass; }
}
