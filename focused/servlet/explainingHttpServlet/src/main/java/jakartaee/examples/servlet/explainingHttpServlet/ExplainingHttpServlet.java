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
package jakartaee.examples.servlet.explainingHttpServlet;

/**
 * ExplainingHttpServlet Web Application
 *
 * @author Ken Fogel (omniprof@gmail.com)
 *
 * Twitter: omniprof - Blog: www.omnijava.com
 *
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExplainingHttpServlet
 *
 * The WebServlet annotation defines this class as a servlet. The description
 * attribute is informative and this will only appear in the app server's
 * console. The default name of the servlet is the name of the class but with
 * the first letter made lowercase. In this class it would be
 * 'explainingHttpServlet'. You can change this, as I have, in the attribute
 * urlPatterns. You can have more than one name for a servlet. This is used when
 * the name used to access the servlet can act as a selector to determine what
 * is expected of the servlet as it can discover what name was used to call it.
 *
 */
@WebServlet(urlPatterns = "/learning")
public class ExplainingHttpServlet extends HttpServlet {

    // All methods log their name just to show the order of calling
    private static final Logger LOG = Logger.getLogger(ExplainingHttpServlet.class.getName());

    /**
     * Can only be used to initialize instance variables or perform tasks before
     * the servlet comes into existence. Instance variables are not thread safe so
     * should rarely be used and then only accessed in a synchronized block.
     * Cannot communicate with the servlet container therefore init is preferred
     * for initialization tasks. Generally not implemented.
     */
    public ExplainingHttpServlet() {
        LOG.info(">>> Constructor <<<");
    }

    /**
     * The servlet initializer
     *
     * Called once the servlet is constructed. Can interact with the servlet
     * container by calling getServletContext(). Preferred method to initialize
     * anything that will be available to every thread.
     *
     * @throws jakarta.servlet.ServletException
     */
    @Override
    public void init() throws ServletException {
        LOG.info(">>> init <<<");
    }

    /**
     * Destructor
     *
     * Java does not have the automatic destructor of C++ only the less than
     * useful finalize(). This method is called by the server just before the
     * servlet is removed from memory. Here you can release any resources that
     * were available to all threads of the servlet that you likely created in
     * init().
     */
    @Override
    public void destroy() {
        LOG.info(">>> destroy <<<");
    }

    /**
     * getter for a String of information
     *
     * Returns information about the servlet, such as author, version, and
     * copyright that you can add. Usually called by the app server similar to
     * how the attribute 'description' is used in @WebServlet.
     *
     * @return Custom string of information about the servlet
     */
    @Override
    public String getServletInfo() {
        LOG.info(">>> getServletInfo <<<");
        return "BasicServlet01 Version 2.0";
    }

    /**
     * Master method for all requests to this servlet
     *
     * Called by the container/app server to allow the servlet to respond to a
     * request. All it does in the super class is figure out which 'do' method
     * is required and proceeds to call it. It allows you to inspect the request
     * and response objects before calling the appropriate do method should you
     * need to. Though rarely overriden this method should end with a call to
     * the super service method unless you plan to do it all yourself.
     *
     * @param request The HttpServletRequest object created by the server
     * @param response The HttpServletResponse object created by the server
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info(">>> service <<<");
        super.service(request, response);
    }

    /**
     * What follows are the four methods doGet, doPost, dotPut, and doDelete,
     * that you can override. These also play a role in RESTful web services.
     * There are three other methods in this family, doHead, doOptions, and
     * doTrace, that you rarely override.
     */
    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a GET request. In a RESTful web service it is CRUD R
     *
     * Retrieves a resource from the server. Must be idempotent and safe. For
     * example, most form queries have no side effects. If a client request is
     * intended to change stored data, the request should use another HTTP
     * method. The contents of the query string is part of the address field so
     * bookmarks include the query string Maximum length between 2K and 8K
     * depending on server and browser though 4K is the average.
     *
     * @param request The HttpServletRequest object created by the server
     * @param response The HttpServletResponse object created by the server
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Output from a Servlet sent to a browser is similar to writing to the
         * console. The HttpServletResponse object knows the path to the user's
         * browser. You begin by declaring the data type. In this example its
         * text/html. In a try-with-resources structure we create a PrintWriter
         * object. With this you can now write to the user's browser. When the
         * PrintWriter closes the text is sent to the browser.
         *
         * Note to pros: You do not have to explicitly flush the PrintWriter as
         * some StackOverflow answers suggest. It flushes when the underlying
         * Writer object has its close method called.
         */
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print(createHTMLString("GET"));
        }
        LOG.info(">>> doGet <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a POST request. In a RESTful web service it is CRUD C
     *
     * Adds a resource to a server. Is not safe or idempotent. Operations
     * requested through POST can have side effects. The query string containing
     * data is in a different part of the http request so bookmarks do not
     * include the query string. No limit on the length.
     *
     * @param request The HttpServletRequest object created by the server
     * @param response The HttpServletResponse object created by the server
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print(createHTMLString("POST"));
        }
        LOG.info(">>> doPost <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a PUT request. In a RESTful web service it is CRUD U
     *
     * Updates an existing resource on the server. It is idempotent but not
     * safe. Used primarily in RESTful web services.
     *
     * Cannot be called from an HTML form. Use curl to access it:
     *
     * curl -X PUT http://localhost:8080/explainingHttpServlet/learning
     *
     * @param request The HttpServletRequest object created by the server
     * @param response The HttpServletResponse object created by the server
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        // One difference is that since this output can only be seen if you use 
        // curl to call this method, the content type is plain text
        response.setContentType("text/plain;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print("You have called doPut");
        }
        LOG.info(">>> doPut <<<");
    }

    /**
     * Called by the server (via the service method) to allow a servlet to
     * handle a DELETE request. In a RESTful web service CRUD D
     *
     * Deletes a resource on the server. It is idempotent but not safe. Used
     * primarily in RESTful web services.
     *
     * Cannot be called from an HTML form. Use curl to access it:
     *
     * curl -X DELETE http://localhost:8080/explainingHttpServlet/learning
     *
     * @param request The HttpServletRequest object created by the server
     * @param response The HttpServletResponse object created by the server
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // See doGet for understanding these lines
        // One difference is that since this output can only be seen if you use 
        // curl to call this method, the content type is plain text
        response.setContentType("text/plain;charset=UTF-8");
        try ( PrintWriter writer = response.getWriter()) {
            writer.print("You have called doDelete");
        }
        LOG.info(">>> doDelete <<<");
    }

    /**
     * This is a utility method to display output. Initially I had this in doGet
     * and doPut but whenever I see code that is fundamentally identical in two
     * places I make it a method and call it when it is needed.
     *
     * @param methodName
     * @return The string containing the HTML to display
     */
    private String createHTMLString(String methodName) {
        String htmlStr = "<html><head><link rel='stylesheet' "
                + "href='styles/main.css' "
                + "type='text/css'/><title>The Learning Servlet</title></head>"
                + "<body><h1>" + methodName + " method</h1>"
                + "<form id='form:index' action = 'index.html'>"
                + "<br/><input type= 'submit' value='Return to Home page' /></form>"
                + "</body></html>";
        return htmlStr;
    }
}
