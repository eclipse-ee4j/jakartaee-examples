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
package jakartaee.examples.faces.managedconverter;

import java.math.BigInteger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 * The Converter for the CDI managed converter example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@FacesConverter(value="managedConverter", managed = true)
public class ManagedConverter implements Converter {
    
    /**
     * Define our default value.
     */
    @Inject
    @ManagedProperty(value = "#{externalContext.requestContextPath}")
    private String defaultValue;

    /**
     * Convert to an object.
     * 
     * @param facesContext the Faces context.
     * @param component the UI component.
     * @param value the string value.
     * @return the object value.
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value.equals("1")) {
            return BigInteger.ONE;
        }
        if (value.equals("2")) {
            return Float.NEGATIVE_INFINITY;
        }
        return defaultValue;
    }

    /**
     * Convert to a string.
     * 
     * @param facesContext the Faces context.
     * @param component the UI component.
     * @param value the object value.
     * @return the string value.
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value instanceof Float) {
            Float floatValue = (Float) value;
            if (floatValue.equals(Float.NEGATIVE_INFINITY)) {
                return "2";
            }
        }
        if (value instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) value;
            if (bigInteger.equals(BigInteger.ONE)) {
                return "1";
            }
        }
        return "0";
    }
}
