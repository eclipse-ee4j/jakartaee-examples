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
package jakartaee.examples.jaxrs.delete;

import java.io.IOException;
import java.net.URL;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * The request scoped bean to submit to the JAX-RS @DELETE resource.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class DeleteHelperBean {

    /**
     * Stores the HTTP servlet request.
     */
    @Inject
    private HttpServletRequest request;

    /**
     * Stores the output.
     */
    private String output;

    /**
     * Set the output.
     *
     * @param output the output.
     */
    public void setOutput(String output) {
        this.output = output;
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
     * Submit to the @DELETE bean.
     *
     * @return ""
     */
    public String submit() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(new URL(request.getScheme(),
                    request.getServerName(),
                    request.getServerPort(),
                    request.getContextPath()).toString() + "/rest/delete");
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            output = httpclient.execute(httpDelete, responseHandler);
        } catch (IOException ioe) {
            output = ioe.getMessage();
        }
        return "";
    }
}
