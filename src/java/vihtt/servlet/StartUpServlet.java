/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vihtt.registation.RegistationDAO;
import vihtt.registation.RegistationDTO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {
    private  final String LOGIN_PAGE = "loginPage" ;
    private  final String  SEARCH_PAGE = "searchPage" ;
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
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        try  {
                HttpSession session = request.getSession();
                if(session.getAttribute("FULLNAME")==null){
                         url=LOGIN_PAGE;
                }else{
                         //1.get cookies
                          Cookie[] cookies = request.getCookies();
                          if (cookies != null) {
                                   //2.get last cookies
                                   String username = "";
                                   String password = "";
                                   for (int i = 0; i < cookies.length; i++) {
                                            if(cookies[i].getName().equals("USERNAME")){
                                                     username = cookies[i].getValue();
                                                     break;
                                            }
                                   }
                                   for (int i = 0; i < cookies.length; i++) {
                                             if(cookies[i].getName().equals("PASSWORD")){
                                                     password = cookies[i].getValue();
                                                     break;
                                            }
                                   }
                                   //3.call check
                                   RegistationDAO dao = new RegistationDAO();
                                   RegistationDTO result = dao.checkLogin(username, password);
                                   if (result != null) {
                                            url = SEARCH_PAGE;
                                            session.setAttribute("FULLNAME", result.getFullname());
                                            session.setAttribute("USERNAME", username);
                                            session.setAttribute("ROLE_OF_USER", result.isRole());
                                   }
                          }
                }
                } catch (SQLException e) {
                            log("StartUpServlet_SQL "+e.getMessage());
                   } catch (NamingException ex) {
                            log("StartUpServlet_Naming "+ex.getMessage());
                   }
        finally{
                  RequestDispatcher RD = request.getRequestDispatcher(url);
                  RD.forward(request, response);
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
