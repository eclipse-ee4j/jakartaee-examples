/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.converter.ejb;

import java.math.BigDecimal;

import jakarta.ejb.Stateless;

/**
 * This is the bean class for the ConverterBean enterprise bean.
 * @author ian
 */
@Stateless
public class ConverterBean {
    private final BigDecimal yenRate = new BigDecimal("104.34");
    private final BigDecimal euroRate = new BigDecimal("0.007");
    
    public BigDecimal dollarToYen(BigDecimal dollars) {
        BigDecimal result = dollars.multiply(yenRate);
        return result.setScale(2, BigDecimal.ROUND_UP);
    }
    
    public BigDecimal yenToEuro(BigDecimal yen) {
        BigDecimal result = yen.multiply(euroRate);
        return result.setScale(2, BigDecimal.ROUND_UP);
    }
}
