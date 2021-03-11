/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vihtt.item.ItemDTO;
import vihtt.cart.cartObject;
import vihtt.item.ItemDAO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "JavaBookServlet", urlPatterns = {"/JavaBookServlet"})
@SuppressWarnings("unchecked") 
public class JavaBookServlet extends HttpServlet {
final String SHOPPING_PAGE = "shoppingPage";
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
        String url = SHOPPING_PAGE;
        try{
                  List<ItemDTO> listItem = new ArrayList<>();
                  ItemDAO dao = new ItemDAO();
                  List<ItemDTO> dto = dao.getAllBook();
                  if(dto!= null){
                           for(ItemDTO i: dto){
                                    listItem.add(i);
                           }
                  }
                  request.setAttribute("LISTBOOK", listItem);
        }catch(SQLException e)  {
                 log("JavaBookServlet_SQL "+e.getMessage());
        }catch(NamingException ex)  {
                 log("JavaBookServlet_Naming "+ex.getMessage());
        }finally{
                 RequestDispatcher rd = request.getRequestDispatcher(url);
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
