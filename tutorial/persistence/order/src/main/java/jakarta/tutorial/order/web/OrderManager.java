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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jakarta.tutorial.order.web;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIParameter;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;
import jakarta.tutorial.order.ejb.RequestBean;
import jakarta.tutorial.order.entity.CustomerOrder;
import jakarta.tutorial.order.entity.LineItem;
import jakarta.tutorial.order.entity.Part;


/**
 *
 * @author ian
 */
@SessionScoped
@Named
public class OrderManager implements Serializable{
    private static final long serialVersionUID = 2142383151318489373L;
    @EJB
    private RequestBean request;
    private static final Logger logger = Logger.getLogger("order.web.OrderManager");
    private List<CustomerOrder> orders;
    private Integer currentOrder;
    private Integer newOrderId;
    private String newOrderShippingInfo;
    private char newOrderStatus;
    private int newOrderDiscount;
    private List<Part> newOrderParts;
    private List<Part> newOrderSelectedParts;
    private String vendorName;
    private List<String> vendorSearchResults;
    private String selectedPartNumber;
    private int selectedPartRevision;
    private Long selectedVendorPartNumber;
    private Boolean findVendorTableDisabled = false;
    private Boolean partsTableDisabled = true;

    /**
     * @return the orders
     */
    public List<CustomerOrder> getOrders() {
        try {
            this.orders = request.getOrders();
        } catch (Exception e) {
            logger.warning("Couldn't get orders.");
        }
        return orders;
    }

    public List<LineItem> getLineItems() {
        try {
            return request.getLineItems(this.currentOrder);
        } catch (Exception e) {
            logger.warning("Couldn't get lineItems.");
            return null;
        }
    }

    public void removeOrder(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteOrderId");
            Integer id = Integer.parseInt(param.getValue().toString());
            request.removeOrder(id);
            logger.log(Level.INFO, "Removed order {0}.", id);
        } catch (NumberFormatException e) {
        }
    }

    public void findVendor() {
        try {
            this.findVendorTableDisabled = true;
            this.vendorSearchResults = (List<String>) request.locateVendorsByPartialName(vendorName);
            logger.log(Level.INFO, "Found {0} vendor(s) using the search string {1}.", 
                    new Object[]{vendorSearchResults.size(), vendorName});
        } catch (Exception e) {
            logger.warning("Problem calling RequestBean.locateVendorsByPartialName from findVendor");
        }
    }

    public void submitOrder() {
        try {
            request.createOrder(newOrderId, newOrderStatus, newOrderDiscount,
                    newOrderShippingInfo);

            logger.log(Level.INFO, "Created new order with order ID {0}, status {1}, "
                    + "discount {2}, and shipping info {3}.", 
                    new Object[]{newOrderId, newOrderStatus, newOrderDiscount, newOrderShippingInfo});
            this.newOrderId = null;
            this.newOrderDiscount = 0;
            this.newOrderParts = null;
            this.newOrderShippingInfo = null;
        } catch (Exception e) {
            logger.warning("Problem creating order in submitOrder.");
        }
    }

    public void addLineItem() {
        try {
            List<LineItem> lineItems = request.getLineItems(currentOrder);
            logger.log(Level.INFO, "There are {0} line items in {1}.", 
                    new Object[]{lineItems.size(), currentOrder});
            request.addLineItem(this.currentOrder,
                    this.selectedPartNumber,
                    this.selectedPartRevision,
                    1);
            logger.log(Level.INFO, "Adding line item to order # {0}", 
                    this.currentOrder);
            //this.clearSelected();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Problem adding line items to order ID {0}", 
                    newOrderId);
        }
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    /**
     * @return the currentOrder
     */
    public int getCurrentOrder() {
        return currentOrder;
    }

    /**
     * @param currentOrder the currentOrder to set
     */
    public void setCurrentOrder(int currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * @return the newOrderId
     */
    public Integer getNewOrderId() {
        return newOrderId;
    }

    /**
     * @param newOrderId the newOrderId to set
     */
    public void setNewOrderId(Integer newOrderId) {
        this.newOrderId = newOrderId;
    }

    /**
     * @return the newOrderShippingInfo
     */
    public String getNewOrderShippingInfo() {
        return newOrderShippingInfo;
    }

    /**
     * @param newOrderShippingInfo the newOrderShippingInfo to set
     */
    public void setNewOrderShippingInfo(String newOrderShippingInfo) {
        this.newOrderShippingInfo = newOrderShippingInfo;
    }

    /**
     * @return the newOrderStatus
     */
    public char getNewOrderStatus() {
        return newOrderStatus;
    }

    /**
     * @param newOrderStatus the newOrderStatus to set
     */
    public void setNewOrderStatus(char newOrderStatus) {
        this.newOrderStatus = newOrderStatus;
    }

    /**
     * @return the newOrderDiscount
     */
    public int getNewOrderDiscount() {
        return newOrderDiscount;
    }

    /**
     * @param newOrderDiscount the newOrderDiscount to set
     */
    public void setNewOrderDiscount(int newOrderDiscount) {
        this.newOrderDiscount = newOrderDiscount;
    }

    /**
     * @return the newOrderParts
     */
    public List<Part> getNewOrderParts() {
        return request.getAllParts();
    }

    /**
     * @param newOrderParts the newOrderParts to set
     */
    public void setNewOrderParts(List<Part> newOrderParts) {
        this.newOrderParts = newOrderParts;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the vendorSearchResults
     */
    public List<String> getVendorSearchResults() {
        return vendorSearchResults;
    }

    /**
     * @param vendorSearchResults the vendorSearchResults to set
     */
    public void setVendorSearchResults(List<String> vendorSearchResults) {
        this.vendorSearchResults = vendorSearchResults;
    }

    /**
     * @return the newOrderSelectedParts
     */
    public List<Part> getNewOrderSelectedParts() {
        return newOrderSelectedParts;
    }

    /**
     * @param newOrderSelectedParts the newOrderSelectedParts to set
     */
    public void setNewOrderSelectedParts(List<Part> newOrderSelectedParts) {
        Iterator<Part> i = newOrderSelectedParts.iterator();
        while (i.hasNext()) {
            Part part = i.next();
            logger.log(Level.INFO, "Selected part {0}.", part.getPartNumber());
        }
        this.newOrderSelectedParts = newOrderSelectedParts;
    }

    /**
     * @return the selectedPartNumber
     */
    public String getSelectedPartNumber() {
        return selectedPartNumber;
    }

    /**
     * @param selectedPartNumber the selectedPartNumber to set
     */
    public void setSelectedPartNumber(String selectedPartNumber) {
        this.selectedPartNumber = selectedPartNumber;
    }

    /**
     * @return the selectedPartRevision
     */
    public int getSelectedPartRevision() {
        return selectedPartRevision;
    }

    /**
     * @param selectedPartRevision the selectedPartRevision to set
     */
    public void setSelectedPartRevision(int selectedPartRevision) {
        this.selectedPartRevision = selectedPartRevision;
    }

    /**
     * @return the selectedVendorPartNumber
     */
    public Long getSelectedVendorPartNumber() {
        return selectedVendorPartNumber;
    }

    /**
     * @param selectedVendorPartNumber the selectedVendorPartNumber to set
     */
    public void setSelectedVendorPartNumber(Long selectedVendorPartNumber) {
        this.selectedVendorPartNumber = selectedVendorPartNumber;
    }

    private void clearSelected() {
        this.setSelectedPartNumber(null);
        this.setSelectedPartRevision(0);
        this.setSelectedVendorPartNumber(0L);
    }

    /**
     * @return the findVendorTableDisabled
     */
    public Boolean getFindVendorTableDisabled() {
        return findVendorTableDisabled;
    }

    /**
     * @param findVendorTableDisabled the findVendorTableDisabled to set
     */
    public void setFindVendorTableDisabled(Boolean findVendorTableDisabled) {
        this.findVendorTableDisabled = findVendorTableDisabled;
    }

    /**
     * @return the partsTableDisabled
     */
    public Boolean getPartsTableDisabled() {
        return partsTableDisabled;
    }

    /**
     * @param partsTableDisabled the partsTableDisabled to set
     */
    public void setPartsTableDisabled(Boolean partsTableDisabled) {
        this.partsTableDisabled = partsTableDisabled;
    }
}
