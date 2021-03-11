/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "CheckOutSevlet", urlPatterns = {"/CheckOutSevlet"})
public class CheckOutSevlet extends HttpServlet {
final String CHECK_OUT = "checkoutPage"; 
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
         String url = CHECK_OUT;
        try {
            HttpSession session = request.getSession(false);
            cartObject cart =(cartObject)session.getAttribute("CUSTCART");
            if(cart == null){
                cart = new cartObject();
            }
            Map<String,Integer> ItemMap = cart.getItems();
            if(ItemMap==null){
                     ItemMap=new HashMap<>();
            }
            ItemDAO dao = new ItemDAO();
            int  totalPrice = 0;
            int totalNum = 0;
            List<ItemDTO> listBookInCart = new ArrayList<>();
                 for (String itemID : ItemMap.keySet()) {
                          ItemDTO dto = dao.getBookByID(itemID,ItemMap.get(itemID));
                          if(dto!=null){
                                   listBookInCart.add(dto);
                                   totalPrice+=dto.getNum()*dto.getPrice();
                                   totalNum+=dto.getNum();
                          }
                 }
                 session.setAttribute("CHECKOUT", listBookInCart);
                 session.setAttribute("TOTAL_PRICE", totalPrice);
                 session.setAttribute("TOTAL_NUM", totalNum);  
        } catch (NumberFormatException e){
                log("CheckOutSevlet_NumberFormat "+e.getMessage());
        } catch (SQLException e){
                 log("CheckOutSevlet_SQL "+e.getMessage());
        } catch (NamingException e){
                 log("CheckOutSevlet_Naming "+e.getMessage());
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
