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
package jakartaee.examples.websocket.encoderdecoder;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

/**
 * The decoder.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class EncoderDecoderDecoder implements Decoder.Text<EncoderDecoder> {

    /**
     * Decode.
     *
     * @param string the string.
     * @return the EncoderDecoder object.
     * @throws DecodeException when a decode error occurs.
     */
    @Override
    public EncoderDecoder decode(String string) throws DecodeException {
        return new EncoderDecoder(string);
    }

    /**
     * In this example We will always decode.
     *
     * @param string the string.
     * @return true.
     */
    @Override
    public boolean willDecode(String string) {
        return true;
    }

    /**
     * Initialized.
     *
     * @param endpointConfig the endpoint configuration.
     */
    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    /**
     * Destroy the decoder.
     */
    @Override
    public void destroy() {
    }
}
