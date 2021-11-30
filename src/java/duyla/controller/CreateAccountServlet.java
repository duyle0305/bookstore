/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.controller;

import duyla.login.LoginCreateError;
import duyla.login.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
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
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        boolean foundErr = false;
        LoginCreateError errors = new LoginCreateError();
        String url = siteMap.getProperty("viewSignUp");
        try  {
           //1. checking all use's errors --> notify to user
           if(username.trim().length() < 6 ||
                   username.trim().length() > 30){
               foundErr = true;
               errors.setUsernameLengtherr("Username is required from 6 to 30 chars");
           }
           if(password.trim().length() < 6 ||
                   password.trim().length() > 20){
               foundErr = true;
               errors.setPasswordLengtherr("Password is required from 6 to 20 chars");
           } else if (!confirm.trim().equals(password.trim())){
               foundErr = true;
               errors.setConfirmNotMatchPassword("Confirm must match Password");
           }
           if(fullName.trim().length() < 2 ||
                   fullName.trim().length() > 50){
               foundErr = true;
               errors.setFullnameLengtherr("Full name is required from 2 to 50 chars");
           }
           if(foundErr){
               request.setAttribute("CREATE_ERRORS", errors);
               return;
           }//end errors are Found
           //2. Assume that no errors. Insert Data to DB
              //call DAO
              LoginDAO dao = new LoginDAO();
              boolean result = dao.createAccount(username, password, fullName, false);
           //3. Assume that no errors occur.  Redirect to login page
           if(result){
               url = siteMap.getProperty("viewLogin");
           }
           
        }catch (SQLException ex){
            String msg = ex.getMessage();
            log("CreateAccountSevrlet SQL" + msg);
            //3.1 if errore occured, showing errors to user page
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is Existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        }catch (NamingException ex){
            log("CreateAccountSevrlet naming" + ex.getMessage());
        }
        finally{
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
