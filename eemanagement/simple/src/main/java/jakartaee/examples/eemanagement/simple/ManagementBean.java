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
package jakartaee.examples.eemanagement.simple;

import java.rmi.RemoteException;
import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.management.j2ee.Management;
import javax.management.j2ee.ManagementHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A request scoped bean for the EE Management Simple example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named
@RequestScoped
public class ManagementBean {

    /**
     * Stores management interface.
     */
    private Management management;

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        try {
            Context context = new InitialContext();
            ManagementHome home = (ManagementHome) context.lookup("java:global/mejb/MEJBBean");
            management = home.create();
        } catch (CreateException | NamingException | RemoteException e) {
        }
    }

    /**
     * Get the management interface.
     *
     * @return the management interface
     */
    public Management getManagement() {
        return management;
    }
}
