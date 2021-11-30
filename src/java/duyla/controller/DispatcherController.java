/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANH DUY
 */
@WebServlet(name = "DispatcherController", urlPatterns = {"/DispatcherController"})
public class DispatcherController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties)context.getAttribute("SITEMAP");

        String button = request.getParameter("btAction");
        String url = siteMap.getProperty("");
        try {
            if (button == null) {
                //check cooking
                url = siteMap.getProperty("processStartUp");
            } else if (button.equals("login")) {
                url = siteMap.getProperty("processLogin");
            } else if (button.equals("Search")) {
                url = siteMap.getProperty("processSearchLastname");
            } else if (button.equals("Delete")) {
                url = siteMap.getProperty("processDeleteAccount");
            } else if (button.equals("Update")) {
                url = siteMap.getProperty("processUpdateAccount");
            } else if (button.equals("Add Book to Your Cart")) {
                url = siteMap.getProperty("processAddBookToCart");
            } else if (button.equals("View Your Cart")) {
                url = siteMap.getProperty("viewCart");
            } else if (button.equals("Remove Selected Books")) {
                url = siteMap.getProperty("processRemoveBookFromCart");
            } else if (button.equals("Create New Account")) {
                url = siteMap.getProperty("processCreateAccount");
            }else if (button.equals("Log Out")) {
                url = siteMap.getProperty("processLogOut");
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
