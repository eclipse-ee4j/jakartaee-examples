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
package jakarta.tutorial.trading.rar.outbound;

import java.util.logging.Logger;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.tutorial.trading.rar.api.TradeConnection;
import jakarta.tutorial.trading.rar.api.TradeConnectionFactory;

/* Implements the class that applications use to request connection 
 * handles to the EIS */
public class TradeConnectionFactoryImpl implements TradeConnectionFactory {

    private static final Logger log = Logger.getLogger("TradeConnectionFactoryImpl");
    private ConnectionManager cmanager;
    private TradeManagedConnectionFactory mcfactory;
    
    /* The container creates instances of this class 
     * through TradeManagedConnectionFactory.createConnectionFactory() */
    TradeConnectionFactoryImpl(TradeManagedConnectionFactory mcfactory,
                               ConnectionManager cmanager) {
        this.mcfactory = mcfactory;
        this.cmanager = cmanager;
    }
    
    /* Applications call this method, which delegates on the container's
     * connection manager to obtain a connection instance through
     * TradeManagedConnectionFactory */
    @Override
    public TradeConnection getConnection() throws ResourceException {
        log.info("[TradeConnectionFactoryImpl] getConnection()");
        return (TradeConnection) cmanager.allocateConnection(mcfactory, null);
    }
}
