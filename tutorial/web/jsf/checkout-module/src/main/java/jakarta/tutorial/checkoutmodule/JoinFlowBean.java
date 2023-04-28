/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.checkoutmodule;

import java.io.Serializable;

import jakarta.faces.flow.FlowScoped;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;

/**
 * Backing bean for JoinFlow.
 */
@Named
@FlowScoped("joinFlow")
public class JoinFlowBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean fanClub;
    private String[] newsletters;
    private static final SelectItem[] newsletterItems = {
        new SelectItem("Duke's Quarterly"),
        new SelectItem("Innovator's Almanac"),
        new SelectItem("Duke's Diet and Exercise Journal"),
        new SelectItem("Random Ramblings")
    };

    public JoinFlowBean() {
        this.newsletters = new String[0];
    }

    public String getReturnValue() {
        return "/exithome";
    }

    public boolean isFanClub() {
        return fanClub;
    }

    public void setFanClub(boolean fanClub) {
        this.fanClub = fanClub;
    }

    public String[] getNewsletters() {
        return newsletters;
    }

    public void setNewsletters(String[] newsletters) {
        this.newsletters = newsletters;
    }

    public SelectItem[] getNewsletterItems() {
        return newsletterItems;
    }

}
