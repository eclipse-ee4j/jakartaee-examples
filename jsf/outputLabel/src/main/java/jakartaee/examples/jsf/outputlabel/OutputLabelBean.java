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
package jakartaee.examples.jsf.outputlabel;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * A request scoped bean for the h:outputLabel example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class OutputLabelBean {
    
    /**
     * Stores the output text.
     */
    private String outputLabel = "'Hello World'";

    /**
     * Get the output label.
     * 
     * @return the output label.
     */
    public String getOutputLabel() {
        return outputLabel;
    }
    
    /**
     * Set the output label.
     * 
     * @param outputLabel the output label.
     */
    public void setOutputLabel(String outputLabel) {
        this.outputLabel = outputLabel;
    }
}
