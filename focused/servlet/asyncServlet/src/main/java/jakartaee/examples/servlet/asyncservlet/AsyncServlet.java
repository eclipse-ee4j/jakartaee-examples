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
package jakartaee.examples.servlet.asyncservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The Servlet for the Servlet API asynchronous Servlet example.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@WebServlet(asyncSupported = true, value = "/*")
public class AsyncServlet extends HttpServlet {

    /**
     * Stores the executor service (for async processing).
     */
    private ScheduledExecutorService executorService;

    /**
     * Stores the queue.
     */
    private BlockingQueue queue;

    /**
     * Start the request.
     *
     * @param request the request.
     * @param response the response.
     * @throws IOException when an I/O error occurs.
     * @throws ServletException when a Servlet error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        queue.add(request.startAsync());
    }

    /**
     * Initialize the servlet.
     *
     * @param config the servlet configuration.
     */
    @Override
    public void init(ServletConfig config) {
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            ArrayList<AsyncContext> clients = new ArrayList<>();
            queue.drainTo(clients);
            clients.parallelStream().forEach((AsyncContext context) -> {
                try {
                    PrintWriter writer = context.getResponse().getWriter();
                    writer.println("OK");
                    writer.flush();
                    context.complete();
                } catch (IOException ioe) {
                }
            });
        }, 0, 2, TimeUnit.SECONDS);
        queue = new ArrayBlockingQueue(1000);
    }
}
