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
package jakartaee.examples.servlet.httpservletmapping;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The servlet for the Servlet API HTTP servlet mapping example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebServlet(urlPatterns = "/*")
public class HttpServletMappingServlet extends HttpServlet {

    /**
     * Process GET.
     * 
     * @param request the request.
     * @param response the response
     * @throws ServletException when a Servlet error occurs.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpServletMapping httpServletMapping = request.getHttpServletMapping();
        writer.println("Servlet name: " + httpServletMapping.getServletName());
        writer.println("Pattern: " + httpServletMapping.getPattern());
        writer.println("Match value: " + httpServletMapping.getMatchValue());
        writer.println("Mapping match: " + httpServletMapping.getMappingMatch().toString());
    }
}
