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
package jakartaee.examples.jsonp.jsonreader;

import java.io.StringReader;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

/**
 * A request scoped bean for the JsonReader example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonReaderBean {

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
        JsonReader jsonReader = Json.createReader(new StringReader("[]"));
        JsonArray array = jsonReader.readArray();
        jsonReader.close();
        output = array.toString();
        return "";
    }
}
