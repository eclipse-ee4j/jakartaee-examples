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
package jakartaee.examples.jsonb.jsonbcreator;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

/**
 * The model object for the JSON-B @JsonbCreator example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class JsonbCreatorExample {

    /**
     * Stores the integer.
     */
    private int integer;

    /**
     * Constructor.
     * 
     * @param integerString the integer in string format.
     */
    @JsonbCreator
    public JsonbCreatorExample(@JsonbProperty("integer") String integerString) {
        integer = Integer.valueOf(integerString);
    }
    
    /**
     * Get the date.
     *
     * @return the date.
     */
    public int getInt() {
        return integer;
    }
}
