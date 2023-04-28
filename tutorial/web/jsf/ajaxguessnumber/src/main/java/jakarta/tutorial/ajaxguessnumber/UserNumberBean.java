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

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserNumberBean implements Serializable {

    private static final long serialVersionUID = 5377607515424187221L;
    @Inject
    DukesNumberBean dukesNumberBean;
    private Integer userNumber = null;
    String response = null;

    public void setUserNumber(Integer user_number) {
        userNumber = user_number;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getResponse() {
        if ((userNumber != null)
                && (userNumber.compareTo(dukesNumberBean.getRandomInt()) == 0)) {
            return "Yay! You got it!";
        }
        if (userNumber == null) {
            return null;
        } else {
            return "Sorry, " + userNumber + " is incorrect.";
        }
    }
}
