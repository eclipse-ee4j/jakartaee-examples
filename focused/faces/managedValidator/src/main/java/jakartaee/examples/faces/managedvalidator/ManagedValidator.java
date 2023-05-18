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
