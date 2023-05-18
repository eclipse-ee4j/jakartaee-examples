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
package jakartaee.examples.websocket.encoderdecoder;

/**
 * The Encoder/Decoder class.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class EncoderDecoder {

    /**
     * Stores the string.
     */
    private final String string;
    
    /**
     * Constructor.
     * 
     * @param string the string.
     */
    public EncoderDecoder(String string) {
        this.string = string;
    }

    /**
     * Return the string representation.
     * 
     * @return the string representation.
     */
    @Override
    public String toString() {
        return string;
    }
}
