/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
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
