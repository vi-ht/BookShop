package vihtt.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vihtt.registation.RegistationDAO;
import vihtt.registation.RegistationDTO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
private final String ERROR_PAGE = "errorPage";
private final String UPDATE_PAGE = "updatePage";
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
        String urlrewriting = ERROR_PAGE;      
        String username = request.getParameter("cfUsername");
        String searchValue = request.getParameter("cfSearchValue");
        String password = request.getParameter("cfPassword");
        String lastname = request.getParameter("cfFullname");
        String roleSTR = request.getParameter("cfRole");
        boolean role = true;
        boolean foundErr = false;
        try {
           if(password.trim().length()<6 || password.trim().length() >20){
                     foundErr = true;
                     request.setAttribute("ERROR","Required typing from 6 to 20 characters!");
           }
           if(!foundErr){
                  RegistationDAO dao = new RegistationDAO();
                  if(roleSTR == null){
                      role = false;
                  }
                  boolean result = dao.UpdateAccount(username, password,role );    
                   if(result){
                      urlrewriting="searchRewriting"+searchValue;
                   }//end if
           }else{
                    urlrewriting = UPDATE_PAGE;
                    request.setAttribute("US", username);
                    request.setAttribute("PW", password);
                    request.setAttribute("LSV",searchValue);
                    request.setAttribute("LN",lastname);
                    request.setAttribute("ROLE",role);
           }          
         } catch (SQLException e) {
                   log("UpdateServlet_SQL "+e.getMessage());
         } catch (NamingException ex) {
                   log("UpdateServlet_Naming "+ex.getMessage());
        }finally{
                RequestDispatcher rd= request.getRequestDispatcher(urlrewriting);
                rd.forward(request, response);
                out.close();
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
