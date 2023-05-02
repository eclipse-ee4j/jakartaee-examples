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
package jakartaee.examples.faces.inputfile;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakartaee.examples.utils.ITBase;

/**
 * The JUnit tests for the h:inputFile example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RunWith(Arquillian.class)
@RunAsClient
public class InputFileIT extends ITBase {

    /**
     * Stores the base URL.
     */
    @ArquillianResource
    private URL baseUrl;

    /**
     * Test the file submit.
     *
     * @throws Exception when a serious error occurs.
     */
    @RunAsClient
    @Test
    public void testFileSubmit() throws Exception {
        HtmlPage page = webClient.getPage(baseUrl);
        HtmlFileInput file = page.getHtmlElementById("form:file");
        file.setData("123".getBytes());
        file.setValueAttribute("index.xhtml");
        file.setContentType("text/plain");
        page = page.getElementById("form:submit").click();
        assertTrue(page.asXml().contains("\"3\""));
    }
}
