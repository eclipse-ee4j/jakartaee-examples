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
package jakartaee.examples.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * The Converter used to demonstrate usage of @Converter.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Converter
public class ConverterConverter implements AttributeConverter<ConverterAttribute, String> {

    /**
     * Convert ConverterAttribute to String.
     * 
     * @param converterAttribute the attribute.
     * @return the string.
     */
    @Override
    public String convertToDatabaseColumn(ConverterAttribute converterAttribute) {
        return converterAttribute.getValue().toString();
    }

    /**
     * Convert String to ConverterAttribute.
     * 
     * @param string the string.
     * @return the ConverterAttribute.
     */
    @Override
    public ConverterAttribute convertToEntityAttribute(String string) {
        return new ConverterAttribute(Long.valueOf(string));
    }
}
