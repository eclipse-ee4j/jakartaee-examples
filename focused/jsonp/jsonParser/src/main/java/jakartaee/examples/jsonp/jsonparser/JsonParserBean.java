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
package jakartaee.examples.jsonp.jsonparser;

import java.io.StringReader;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

/**
 * A request scoped bean for the JsonParser example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonParserBean {

    /**
     * Stores the input.
     */
    private String input;

    /**
     * Stores the output.
     */
    private String output;

    /**
     * Get the input.
     *
     * @return the input.
     */
    public String getInput() {
        return input;
    }

    /**
     * Get the output.
     *
     * @return the output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Set the input.
     *
     * @param input the input.
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        JsonParser parser = Json.createParser(new StringReader(input));
        StringBuilder builder = new StringBuilder();
        while(parser.hasNext()) {
            Event event = parser.next();
            builder.append(event.toString()).append("\n");
        }
        output = builder.toString();
        return "";
    }
}
