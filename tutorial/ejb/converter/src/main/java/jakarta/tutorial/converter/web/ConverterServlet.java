/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakarta.tutorial.converter.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.tutorial.converter.ejb.ConverterBean;

@WebServlet(urlPatterns="/")
public class ConverterServlet extends HttpServlet {
    private static final long serialVersionUID = -8312407323476917087L;
    @EJB
    ConverterBean converter;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Output the results
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<title>Servlet ConverterServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ConverterServlet at " +
                request.getContextPath() + "</h1>");
        try {
            String amount = request.getParameter("amount");
            if (amount != null && amount.length() > 0) {
                // convert the amount to a BigDecimal from the request parameter
                BigDecimal d = new BigDecimal(amount);
                // call the ConverterBean.dollarToYen() method to get the amount
                // in Yen
                BigDecimal yenAmount = converter.dollarToYen(d);

                // call the ConverterBean.yenToEuro() method to get the amount
                // in Euros
                BigDecimal euroAmount = converter.yenToEuro(yenAmount);

                out.println("<p>" + amount + " dollars are " +
                        yenAmount.toPlainString() + " yen.</p>");
                out.println("<p>" + yenAmount.toPlainString() + " yen are " +
                        euroAmount.toPlainString() + " Euro.</p>");
            } else {
                out.println("<p>Enter a dollar amount to convert:</p>");
                out.println("<form method=\"get\">");
                out.println("<p>$ <input title=\"Amount\" type=\"text\" name=\"amount\" size=\"25\"></p>");
                out.println("<br/>");
                out.println("<input type=\"submit\" value=\"Submit\">" +
                        "<input type=\"reset\" value=\"Reset\">");
                out.println("</form>");
            }

        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
