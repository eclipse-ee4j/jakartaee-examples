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

/* Represents the response to a trade from the EIS */
public class TradeResponse {

    public enum Status { EXECUTED, FAILED };
    
    private Status status;
    private int orderNumber;
    private double total;
    private double fee;
    
    public TradeResponse() { }
    
    public TradeResponse(String resp) {
        String[] words = resp.split(" ");
        if (words[0].compareTo("EXECUTED") == 0)
            status = Status.EXECUTED;
        else
            status = Status.FAILED;
        orderNumber = Integer.parseInt(words[1].substring(1));
        total = Double.parseDouble(words[3]);
        fee = Double.parseDouble(words[5]);
    }
    
    @Override
    public String toString() {
        return String.format("EXECUTED #%d TOTAL %.2f FEE %.2f",
                             orderNumber, total, fee);
    }
    
    /* Getters and setters */
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public int getOrderNumber() { return orderNumber; }
    public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }
}
