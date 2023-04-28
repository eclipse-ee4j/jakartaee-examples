/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
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
