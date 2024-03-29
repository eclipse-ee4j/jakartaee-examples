/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakarta.tutorial.order.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 *
 * @author ian
 */
@Singleton
@Startup
public class ConfigBean {

    @EJB
    private RequestBean request;

    @PostConstruct
    public void createData() {
        request.createPart("1234-5678-01", 1, "ABC PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        request.createPart("9876-4321-02", 2, "DEF PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        request.createPart("5456-6789-03", 3, "GHI PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        request.createPart("ABCD-XYZW-FF", 5, "XYZ PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);
        request.createPart("SDFG-ERTY-BN", 7, "BOM PART",
                new java.util.Date(), "PARTQWERTYUIOPASXDCFVGBHNJMKL", null);

        request.addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "1234-5678-01", 1);
        request.addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "9876-4321-02", 2);
        request.addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "5456-6789-03", 3);
        request.addPartToBillOfMaterial("SDFG-ERTY-BN", 7,
                "ABCD-XYZW-FF", 5);

        request.createVendor(100, "WidgetCorp",
                "111 Main St., Anytown, KY 99999", "Mr. Jones",
                "888-777-9999");
        request.createVendor(200, "Gadget, Inc.",
                "123 State St., Sometown, MI 88888", "Mrs. Smith",
                "866-345-6789");

        request.createVendorPart("1234-5678-01", 1,
                "PART1", 100.00, 100);
        request.createVendorPart("9876-4321-02", 2,
                "PART2", 10.44, 200);
        request.createVendorPart("5456-6789-03", 3,
                "PART3", 76.23, 200);
        request.createVendorPart("ABCD-XYZW-FF", 5,
                "PART4", 55.19, 100);
        request.createVendorPart("SDFG-ERTY-BN", 7,
                "PART5", 345.87, 100);

        Integer orderId = 1111;
        request.createOrder(orderId, 'N', 10,
                "333 New Court, New City, CA 90000");
        request.addLineItem(orderId, "1234-5678-01", 1, 3);
        request.addLineItem(orderId, "9876-4321-02", 2, 5);
        request.addLineItem(orderId, "ABCD-XYZW-FF", 5, 7);

        orderId = 4312;
        request.createOrder(orderId, 'N', 0,
                "333 New Court, New City, CA 90000");
        request.addLineItem(orderId, "SDFG-ERTY-BN", 7, 1);
        request.addLineItem(orderId, "ABCD-XYZW-FF", 5, 3);
        request.addLineItem(orderId, "1234-5678-01", 1, 15);
    }

    @PreDestroy
    public void deleteData() {
        
    }
}
