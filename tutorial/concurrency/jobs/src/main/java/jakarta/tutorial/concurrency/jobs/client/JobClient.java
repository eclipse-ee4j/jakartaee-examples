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
package jakarta.tutorial.concurrency.jobs.client;


import java.io.Serializable;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

/**
 * Client to JAXRS service
 *
 * @author markito
 */
@Named
@RequestScoped
public class JobClient implements Serializable {
    private final static Logger logger = Logger.getLogger(JobClient.class.getCanonicalName());
    private static final long serialVersionUID = 16472027766900196L;

    private String token;
    private int jobID;

    private final String serviceEndpoint = "http://localhost:8080/jobs/webapi/JobService/process";

    public String submit() {
        final Client client = ClientBuilder.newClient();

        final Response response = client.target(serviceEndpoint)
                .queryParam("jobID", getJobID())
                .request()
                .header("X-REST-API-Key", token)
                .post(null);

        FacesMessage message;
        message = (response.getStatus() == 200)
                ? new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("Job %d successfully submitted",getJobID()), null)
                : new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format("Job %d was NOT submitted",getJobID()), null);

        FacesContext.getCurrentInstance().addMessage(null, message);
        logger.info(message.getSummary());
        clear();
        return "";
    }
    private void clear() {
        this.token = "";
    }
    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the jobID
     */
    public int getJobID() {
        return jobID;
    }

    /**
     * @param jobID the jobID to set
     */
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
}
