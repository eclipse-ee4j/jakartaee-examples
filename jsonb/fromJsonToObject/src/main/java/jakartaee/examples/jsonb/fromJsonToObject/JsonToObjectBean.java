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
package jakartaee.examples.jsonb.fromJsonToObject;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.bind.JsonbBuilder;

/**
 * A request scoped bean for using with the JSON to Object example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "jsonToObjectBean")
@RequestScoped
public class JsonToObjectBean {
    
    /**
     * Stores the JSON.
     */
    private String json;
    
    /**
     * Stores the list.
     */
    private List list;

    /**
     * Get the JSON.
     * 
     * @return the JSON.
     */
    public String getJson() {
        return json;
    }
    
    /**
     * Get the list.
     * 
     * @return the list.
     */
    public List getList() {
        return list;
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
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        list = builder.build().fromJson("[" + json + "]", List.class);
        return "";
    }
}
