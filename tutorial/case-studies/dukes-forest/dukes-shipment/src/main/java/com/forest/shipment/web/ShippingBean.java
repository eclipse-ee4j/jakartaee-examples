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
package com.forest.shipment.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.entity.CustomerOrder;
import com.forest.shipment.ejb.OrderBrowser;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Named
@RequestScoped
public class ShippingBean implements Serializable {

    private static final Logger logger =
            Logger.getLogger(ShippingBean.class.getCanonicalName());
    private static final String SERVICE_ENDPOINT =
            "http://localhost:8080/dukes-store/services/orders";
    private static final String MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private static final long serialVersionUID = -2526289536313985021L;
    protected Client client;
    @EJB
    OrderBrowser orderBrowser;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    private void clean() {
        client.close();
    }
    private Map<String, CustomerOrder> orders;

    /**
     * @return the orders
     */
    public Map<String, CustomerOrder> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Map<String, CustomerOrder> orders) {
        this.orders = orders;
    }

    public enum Status {

        PENDING_PAYMENT(2),
        READY_TO_SHIP(3),
        SHIPPED(4),
        CANCELLED_PAYMENT(5),
        CANCELLED_MANUAL(6);
        private int status;

        private Status(final int pStatus) {
            status = pStatus;
        }

        public int getStatus() {
            return status;
        }
    }

    public String getEndpoint() {
        return SERVICE_ENDPOINT;
    }

    public List<CustomerOrder> listByStatus(final Status status) {
        List<CustomerOrder> entity = (List<CustomerOrder>) client.target(SERVICE_ENDPOINT)
                .queryParam("status", String.valueOf(status.getStatus()))
                .request(MEDIA_TYPE)
                .get(new GenericType<List<CustomerOrder>>() {
        });

        return entity;
    }

    public void updateOrderStatus(final String messageID, final Status status) {
        // consume message
        CustomerOrder order = orderBrowser.processOrder(messageID);

        // call order service to update db in Store
        Response response = client.target(SERVICE_ENDPOINT)
                .path("/" + order.getId())
                .request(MEDIA_TYPE)
                .put(Entity.text(String.valueOf(status.getStatus())));

        logger.log(Level.FINEST, "PUT Status response: {0}", response.getStatus());
    }

    /**
     * @return the orders
     */
    public List<String> getPendingOrders() {
        Map<String, CustomerOrder> pendingOrders = orderBrowser.getOrders();

        if (pendingOrders == null) {
            return null;
        } else {
            // update current pending orders map
            setOrders(pendingOrders);
            return new ArrayList<>(getOrders().keySet());
        }
    }

    public List<CustomerOrder> getCompletedOrders() {
        return listByStatus(Status.SHIPPED);
    }
}
