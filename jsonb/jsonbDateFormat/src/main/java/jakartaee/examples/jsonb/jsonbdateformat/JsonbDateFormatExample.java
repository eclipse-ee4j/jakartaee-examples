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

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 * The model object for the JSON-B @JsonbDateFormat example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class JsonbDateFormatExample {

    /**
     * Stores the date.
     */
    @JsonbDateFormat(value = "MM/dd/yyyy")
    private Date date;

    /**
     * Get the date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date.
     * 
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
