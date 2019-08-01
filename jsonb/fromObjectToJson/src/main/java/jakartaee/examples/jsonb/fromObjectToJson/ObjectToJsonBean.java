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
package jakartaee.examples.jsonb.fromObjectToJson;

import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.bind.JsonbBuilder;

/**
 * A request scoped bean for using with the Object to JSON example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "objectToJsonBean")
@RequestScoped
public class ObjectToJsonBean {

    /**
     * Stores the json.
     */
    private String json;

    /**
     * Stores the string.
     */
    private String string;

    /**
     * Get the JSON.
     *
     * @return the JSON.
     */
    public String getJson() {
        return json;
    }

    /**
     * Get the string.
     *
     * @return the string.
     */
    public String getString() {
        return string;
    }

    /**
     * Set the JSON.
     *
     * @param json the json.
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Set the string.
     *
     * @param string the string.
     */
    public void setString(String string) {
        this.string = string;
    }
    
    /**
     * Submit.
     *
     * @return ""
     */
    public String submit() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        ArrayList list = new ArrayList<>();
        list.add(string);
        json = builder.build().toJson(list);
        return "";
    }
}
