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
package jakartaee.examples.jsonb.jsonbdateformat;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

/**
 * The request scoped bean for the JSON-B @JsonbDateFormat example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class JsonbDateFormatBean {
    
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
        JsonbConfig config = new JsonbConfig();
        config.withLocale(Locale.US);
        JsonbBuilder builder = JsonbBuilder.newBuilder().withConfig(config);
        JsonbDateFormatExample example = new JsonbDateFormatExample();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("MST"));
        calendar.set(2001, 8, 11);
        example.setDate(calendar.getTime());
        output = builder.build().toJson(example);
        return "";
    }
}
