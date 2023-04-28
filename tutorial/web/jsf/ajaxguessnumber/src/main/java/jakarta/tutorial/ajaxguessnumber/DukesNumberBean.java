/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.ajaxguessnumber;

import java.io.Serializable;
import java.util.Random;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 *
 * @author ievans
 */
@Named
@SessionScoped
public class DukesNumberBean implements Serializable {

    private static final long serialVersionUID = 6575056551121951958L;
    private Integer randomInt = null;
    private long maximum = 10;
    private long minimum = 0;

    public DukesNumberBean() {
        Random randomGR = new Random();
        long range = maximum + minimum + 1;
        randomInt = (int) (minimum + randomGR.nextDouble() * range);
        System.out.println("Duke's number: " + randomInt);
    }

    public long getMaximum() {
        return (this.maximum);
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public long getMinimum() {
        return (this.minimum);
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    /**
     * @return the randomInt
     */
    public Integer getRandomInt() {
        return randomInt;
    }

    /**
     * @param randomInt the randomInt to set
     */
    public void setRandomInt(Integer randomInt) {
        this.randomInt = randomInt;
    }
}
