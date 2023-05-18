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
package jakartaee.examples.ejb.securedremoteejb.appclient;

import jakartaee.examples.ejb.securedremoteejb.SecuredRemoteEjb;
import jakarta.ejb.EJB;

/**
 * A secured remote EJB application client.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class Client {

    /**
     * Stores the remote EJB.
     */
    @EJB
    private static SecuredRemoteEjb ejb;

    /**
     * Run method.
     */
    public void run() {
        System.out.println(ejb.helloWorld());
    }

    /**
     * Main method.
     * 
     * <p>
     *  If you are executing this on Glassfish / Payara deploy the EAR to the 
     *  server first then build the client application, and then go into the
     *  target directory and execute the command line mentioned below.
     * </p>
     *
     * <pre>
     *   appclient -client appclient-8-SNAPSHOT.jar -user securedRemoteEjbUser -password password
     * </pre>
     * 
     * <p>
     *  Note this assumes you have setup the username 'securedRemoteEjbUser' 
     *  with password 'password' in the 'file' realm.
     * </p>
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        Client main = new Client();
        main.run();
    }
}
