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
package jakartaee.examples.jsf.actionlistener;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 * The bean for the f:actionListener example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class ActionListenerBean {
    
    /**
     * Stores the output text.
     */
    private String outputText = "'Hello World'";

    /**
     * Get the outputText.
     * 
     * @return the outputText.
     */
    public String getOutputText() {
        return outputText;
    }
    
    /**
     * Set the outputText.
     * 
     * @param outputText the outputText.
     */
    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }
    
    /**
     * Submit the form.
     * 
     * @return ""
     */
    public String submit() {
        outputText = "And you just submitted the form";
        return "";
    }
}
