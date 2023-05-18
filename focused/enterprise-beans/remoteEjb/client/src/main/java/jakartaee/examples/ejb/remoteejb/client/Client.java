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
package jakartaee.examples.ejb.remoteejb.client;

import jakartaee.examples.ejb.remoteejb.RemoteEjb;
import javax.naming.InitialContext;

/**
 * The remoteEjb client.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class Client {

    /**
     * Main method.
     * 
     * <p>
     *  If you are running this on Glassfish/Payara and you want to connect to
     *  a specific host the following properties would need to be used with the
     *  InitialContext.
     * </p>
     * <pre>
     *  properties.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
     *  properties.put("org.omg.CORBA.ORBInitialHost", "localhost");
     *  properties.put("org.omg.CORBA.ORBInitialPort", "3700");
     * </pre>
     *
     * @param arguments the arguments.
     * @throws Exception when an error occurs.
     */
    public static void main(String[] arguments) throws Exception {
        InitialContext initialContext = new InitialContext();
        RemoteEjb ejb = (RemoteEjb) initialContext.lookup("java:global/remoteEjb/remoteEjbJar/remoteEjb");
        System.out.println(ejb.helloWorld());
    }
}
