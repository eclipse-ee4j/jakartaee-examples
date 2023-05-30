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
package jakartaee.examples.faces.viewscoped;

import java.io.Serializable;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * A view scoped bean.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "viewScopedBean")
@ViewScoped
public class ViewScopedBean implements Serializable {

    /**
     * Stores the time.
     */
    private String time;

    /**
     * Different view.
     *
     * @return "different"
     */
    public String differentView() {
        return "different";
    }

    /**
     * Index view.
     *
     * @return "index"
     */
    public String indexView() {
        return "index";
    }

    /**
     * Initialize the bean.
     */
    @PostConstruct
    public void initialize() {
        time = new Date().toString() + " - " + System.nanoTime();
    }

    /**
     * Get the time.
     *
     * @return the time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Same view.
     *
     * @return ""
     */
    public String sameView() {
        return "";
    }
}
