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
package com.forest.web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.entity.Groups;
import com.forest.entity.Person;
import com.forest.qualifiers.LoggedIn;
import com.forest.web.util.JsfUtil;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author markito
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
    
    private static final String BUNDLE = "bundles.Bundle";
    private static final long serialVersionUID = -8851462237612818158L;

    Person user;
    @EJB
    private com.forest.ejb.UserBean ejbFacade;
    private String username;
    private String password;
    @Inject
    CustomerController customerController;

    /**
     * Creates a new instance of Login
     */
    public UserController() {
    }

    /**
     * Login method based on
     * <code>HttpServletRequest</code> and security realm
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String result;

        try {
            request.login(this.getUsername(), this.getPassword());

            JsfUtil.addSuccessMessage(JsfUtil.getStringFromBundle(BUNDLE, "Login_Success"));

            this.user = ejbFacade.getUserByEmail(getUsername());
            this.getAuthenticatedUser();

            if (isAdmin()) {
                result = "/admin/index";
            } else {
                result = "/index";
            }

        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "Login_Failed"));

            result = "login";
        }

        return result;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            this.user = null;
            
            request.logout();
            // clear the session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            
            JsfUtil.addSuccessMessage(JsfUtil.getStringFromBundle(BUNDLE, "Logout_Success"));

        } catch (ServletException ex) {

            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "Logout_Failed"));
        } finally {
            return "/index";
        }
    }

    /**
     * @return the ejbFacade
     */
    public com.forest.ejb.UserBean getEjbFacade() {
        return ejbFacade;
    }

    public @Produces
    @LoggedIn
    Person getAuthenticatedUser() {
        return user;
    }

    public boolean isLogged() {
        return (getUser() == null) ? false : true;
    }

    public boolean isAdmin() {
        for (Groups g : user.getGroupsList()) {
            if (g.getName().equals("ADMINS")) {
                return true;
            }
        }
        return false;
    }

    public String goAdmin() {
        if (isAdmin()) {
            return "/admin/index";
        } else {
            return "index";
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public Person getUser() {
        return user;
    }
}
