package jakarta.tutorial.web.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/greeting")
public class Greeting extends HttpServlet {

    @Override
    public void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        var name = request.getParameter("name");

        if (name == null || name.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        var greeting = "Hello, " + name + "!";

        response.setContentType("text/plain");
        response.getWriter().write(greeting);
    }
}