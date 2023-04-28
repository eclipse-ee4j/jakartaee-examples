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
package jakartaee.examples.jsonp.jsonwriter;

import java.io.StringWriter;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonWriter;

/**
 * A request scoped bean for the JsonWriter example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonWriterBean {

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
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stringWriter);
        jsonWriter.writeArray(Json.createArrayBuilder().build());
        output = stringWriter.toString();
        return "";
    }
}
