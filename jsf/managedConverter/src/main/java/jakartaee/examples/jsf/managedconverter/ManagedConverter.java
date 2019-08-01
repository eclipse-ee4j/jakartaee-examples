/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.jsf.managedconverter;

import java.math.BigInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

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
