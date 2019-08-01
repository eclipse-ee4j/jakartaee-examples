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
package jakartaee.examples.jsonp.jsongenerator;

import java.io.StringWriter;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.stream.JsonGenerator;

/**
 * A request scoped bean for the JsonGenerator example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonGeneratorBean {


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
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject();
        generator.write("property", "propertyValue");
        generator.writeEnd();
        generator.flush();
        output = writer.toString();
        return "";
    }
}
