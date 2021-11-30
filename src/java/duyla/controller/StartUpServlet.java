/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.controller;

import duyla.login.LoginDAO;
import duyla.login.LoginDTO;
import duyla.util.MyApplicationConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ANH DUY
 */
@WebServlet(name = "StartUpSevrlet", urlPatterns = {"/StartUpSevrlet"})
public class StartUpServlet extends HttpServlet {

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
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        String url = siteMap.getProperty("");
        try {
            //1.get cookies from clien of specific domain
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. get last cookies
                //Cookie lastCookie = cookies[0];
                for (Cookie cookie : cookies) {
                    //3. get username and password from last,cookies
                    String username = cookie.getName();
                    String password = cookie.getValue();
                    //4. call DAO to check Login
                    LoginDAO dao = new LoginDAO();
                    String result = dao.getFullname(username, password);
                    if (result != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", result);
                        url = siteMap.getProperty(MyApplicationConstant.LoginFeature.VIEW_SEARCH);
                    }//end if check login is success - user is exised
                }
            }//end if cookies existed
        } catch (SQLException ex) {
            log("LoginSevrlet naming" + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginSevrlet SQL" + ex.getMessage());
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
