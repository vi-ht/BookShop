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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
private  final String SEARCH_PAGE = "searchPage";
private  final String INVALID_PAGE = "invalidPage";
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
            String url = INVALID_PAGE;
            PrintWriter out = response.getWriter();
            String button =request.getParameter("btAction");
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPass");
             try {
                        RegistationDAO dao = new RegistationDAO();
                        RegistationDTO result = dao.checkLogin(username, password);
                        if (result != null) {
                            url=SEARCH_PAGE;
                            HttpSession session = request.getSession();
                            session.removeAttribute("LOGOUT");
                            session.setAttribute("FULLNAME", result.getFullname());
                            session.setAttribute("USERNAME", username);
                            session.setAttribute("ROLE_OF_USER", result.isRole());
                            Cookie us = new Cookie("USERNAME",username);
                            Cookie pw = new Cookie("PASSWORD", password);
                            us.setMaxAge(60*3*60);
                            pw.setMaxAge(60*3*60);
                            response.addCookie(us);
                            response.addCookie(pw);
                        }//end  if
                   } catch (SQLException e) {
                            log("LoginServlet_SQL "+e.getMessage());
                   } catch (NamingException ex) {
                            log("LoginServlet_Naming "+ex.getMessage());
                   }
             finally{
                response.sendRedirect(url);
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
