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
package jakartaee.examples.jsonb.jsonbtransient;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.bind.JsonbBuilder;

/**
 * The request scoped bean for the @JsonbTransient example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonbTransientBean {
    
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
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        JsonbTransientExample example = new JsonbTransientExample();
        example.setString("It does not matter what I set this to, it will not show up in the output");
        output = builder.build().toJson(example);
        return "";
    }
}
