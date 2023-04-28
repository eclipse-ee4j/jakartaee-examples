/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jakarta.tutorial.async.web;

import java.io.Serializable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.tutorial.async.ejb.MailerBean;

/**
 *
 * @author ievans
 */
@Named
@SessionScoped
public class MailerManagedBean implements Serializable {

    @EJB
    protected MailerBean mailerBean;
    protected String email;
    protected String status;
    private static final Logger logger = Logger.getLogger(MailerManagedBean.class.getName());
    private Future<String> mailStatus;

    /**
     * Creates a new instance of MailerManagedBean
     */
    public MailerManagedBean() {
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        if (mailStatus.isDone()) {
            try {
                this.setStatus(mailStatus.get());
            } catch (ExecutionException | CancellationException |
                    InterruptedException ex) {
                this.setStatus(ex.getCause().toString());
            }
        }
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String send() {
        String response = "response?faces-redirect=true";
        try {
            mailStatus = mailerBean.sendMessage(this.getEmail());
            this.setStatus("Processing... (refresh to check again)");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }

        return response;
    }

}
