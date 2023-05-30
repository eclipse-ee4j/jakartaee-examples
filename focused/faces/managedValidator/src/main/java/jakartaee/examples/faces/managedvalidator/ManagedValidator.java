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
package jakartaee.examples.faces.managedvalidator;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;

/**
 * The Validator for the CDI managed validator example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@FacesValidator(value = "managedValidator", managed = true)
public class ManagedValidator implements Validator {

    /**
     * Define our default value.
     */
    @Inject
    @ManagedProperty(value = "#{externalContext.requestContextPath}")
    private String defaultValue;

    /**
     * Validate.
     *
     * @param facesContext the Faces context.
     * @param component the UI component.
     * @param value the value.
     * @throws ValidatorException when validation fails.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        if (!value.toString().equals(defaultValue)) {
            throw new ValidatorException(new FacesMessage(
                    "The value should be: " + defaultValue, "The value should be: " + defaultValue));
        }
    }
}
