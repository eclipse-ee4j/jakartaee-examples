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
package jakarta.tutorial.trading.war;

import java.io.Serializable;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.resource.ConnectionFactoryDefinition;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.TransactionSupport;
import jakarta.tutorial.trading.rar.api.TradeConnection;
import jakarta.tutorial.trading.rar.api.TradeConnectionFactory;
import jakarta.tutorial.trading.rar.api.TradeOrder;
import jakarta.tutorial.trading.rar.api.TradeProcessingException;
import jakarta.tutorial.trading.rar.api.TradeResponse;

/* Managed bean for JSF pages that uses the RA Common Client Interface (CCI)
 * to submit trades to the EIS. */
@Named
@SessionScoped
@ConnectionFactoryDefinition(
    name = "java:comp/env/eis/TradeConnectionFactory",
    interfaceName = "jakarta.tutorial.trading.rar.api.TradeConnectionFactory",
    resourceAdapter = "#trading-rar",
    minPoolSize = 5,
    transactionSupport = 
            TransactionSupport.TransactionSupportLevel.NoTransaction
)
public class ResourceAccessBean implements Serializable {

    private static final long serialVersionUID = -3002431810375279862L;
    private static final Logger log = Logger.getLogger("TradeBean");
    
    @Resource(lookup = "java:comp/env/eis/TradeConnectionFactory")
    private TradeConnectionFactory connectionFactory;
    
    private TradeConnection connection = null;
    private final TradeOrder order;
    private TradeResponse response;
    private String infoBox = "";
    
    public ResourceAccessBean() {
        order = new TradeOrder();
        order.setNShares(1000);
        order.setTicker(TradeOrder.Ticker.YYYY);
        order.setOrderType(TradeOrder.OrderType.BUY);
        order.setOrderClass(TradeOrder.OrderClass.MARKET);
    }
    
    /* JSF navigation method (from index.xhtml) */
    public String connect() {
        String page = "index";
        if (connection == null) {
            try {
                log.info("[ResourceAccessBean] Getting connection from the RA");
                connection = connectionFactory.getConnection();
                page = "trade";
            } catch (ResourceException e) {
                log.info(e.getMessage());
            }
        }
        return page;
        
    }
    
    /* JSF navigation method (from trade.xhtml) */
    public String disconnect() {
        infoBox = "";
        try {
            connection.close();
            connection = null;
        } catch (ResourceException e) {
            log.info(e.getMessage());
        }
        return "index";
    }
    
    /* JSF interface method to submit a trade to the RA/EIS (in trade.xhtml) */
    public void submitTrade() {
        infoBox = "\n -->" + order.toString() + infoBox;
        /* Use the Common Client Interface */
        try {
            response = connection.submitOrder(order);
            infoBox = "\n <--" + response.toString() + infoBox;
        } catch (TradeProcessingException ex) {
            infoBox = "\n <-- ERROR " + ex.getMessage() + infoBox;
        }
        
    }
    
    /* Getters and setters */
    public String getHost() { return "localhost"; }
    public int getPort() { return 4004; }
    public TradeOrder getOrder() { return order; }
    public void setInfoBox(String infoBox) { this.infoBox = infoBox; }
    public String getInfoBox() { return infoBox; }
    public TradeOrder.OrderClass[] getOrderClassList() {
        return TradeOrder.OrderClass.values();
    }
    public TradeOrder.OrderType[] getOrderTypeList() {
        return TradeOrder.OrderType.values();
    }
    public TradeOrder.Ticker[] getTickerList() {
        return TradeOrder.Ticker.values();
    }
}
