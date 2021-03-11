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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vihtt.registation.RegistationDAO;
import vihtt.registation.registationCreateError;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
final String ERROR_PAGE = "errorPage";
final String LOGIN_PAGE = "loginPage";
final String CREATE_PAGE = "createPageJSP";

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
                  String username= request.getParameter("txtUsername");
                  String pass = request.getParameter("txtPass");
                  String confirm= request.getParameter("txtConfirm");
                  String fullname= request.getParameter("txtFullname");
                  String url = ERROR_PAGE;
                  registationCreateError errors = new registationCreateError();
                  try {
                        //1,checkvalidate
                        boolean foundErr = false;
                        //k check null vi parameter k ton tai thi bang null
                        if(username.trim().length()<6 || username.trim().length() >30){
                                 foundErr = true;
                                 errors.setUsernameLenghERROR("Required typing from 6 to 30 characters!");
                        }
                        if(pass.trim().length()<6 || pass.trim().length() >20){
                                 foundErr = true;
                                 errors.setPassLenghERROR("Required typing from 6 to 20 characters!");
                        }else if(!pass.trim().equals(confirm.trim())){
                                  foundErr = true;
                                  errors.setConfirmNotMatchERROR("Confirm password is not match!");
                        }
                        if(fullname.trim().length()<2 || fullname.trim().length() >50){
                                 foundErr = true;
                                  errors.setFullnameLenghERROR("Required typing from 2 to 50 characters!");
                        }
                        if(foundErr){
                                 //2.store Error into scope
                                 request.setAttribute("CREATE_ERR", errors);
                                 url = CREATE_PAGE;
                        }else{
                                 RegistationDAO dao = new RegistationDAO();
                                 boolean result = dao.createAccount(username, pass, fullname, false);
                                 if(result){
                                          url = LOGIN_PAGE;
                                 }
                        }
                         } catch (SQLException e){
                                   String errMess = e.getMessage();
                                    log("createNewAccountServlet_SQL"+e.getMessage());
                                    if(errMess.contains("duplicate")){
                                             errors.setUsernameIsExistERROR(username+" is existed!");
                                             request.setAttribute("CREATE_ERR", errors);
                                             url = CREATE_PAGE;
                                    }
                         } catch (NamingException e){
                                    log("createnewAccountServlet_Name"+e.getMessage());
                  }finally{
                           RequestDispatcher rd= request.getRequestDispatcher(url);
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
