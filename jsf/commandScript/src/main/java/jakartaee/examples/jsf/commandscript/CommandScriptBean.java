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
package jakartaee.examples.jsf.commandscript;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * A request scoped bean for the h:commandScript example.
 * ˆ
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "commandScriptBean")
@RequestScoped
public class CommandScriptBean implements Serializable {
    
    /**
     * Stores the output.
     */
    private String output;

    /**
     * Get the output.
     * 
     * @return the output.
     */
    public String getOutput() {
        return output;
    }
    
    /**
     * Process the call.
     * 
     * @return null
     */
    public Object process() {
        output = "And we processed the call";
        return null;
    }
}
