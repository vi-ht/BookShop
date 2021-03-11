/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import vihtt.bill.BillCheckoutError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import vihtt.bill.BillDAO;
import vihtt.cart.CartDAO;
import vihtt.item.ItemDAO;


/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {
private final String ERROR_PAGE = "errorPage";
private final String SHOPPING_PAGE_SUCCESS = "successPage";
private final String CHECKOUT_PAGE="checkoutPage";

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
         String Name = request.getParameter("txtCustName");
         String Adress = request.getParameter("txtAddress");
         String Phone = request.getParameter("txtPhone");
         String TotalPrice = request.getParameter("txtTotal");
         String url = ERROR_PAGE;
         boolean foundErr = false;
         BillCheckoutError errors = new BillCheckoutError();
      try {
               if(Name.trim().length()<2||Name.length()>50){
                        foundErr=true;
                        errors.setNameLengthERROR("Required typing from 2 to 50 characters!");
               }
                if(Adress.trim().length()<2||Adress.length()>200){
                        foundErr=true;
                        errors.setAdressLengthERROR("Required typing from 2 to 200 characters!");
               }
               if(Phone.trim().length()<3||Phone.length()>15){
                        foundErr=true;
                        errors.setPhoneLengthERROR("Required typing from 3 to 15 characters!");
               }else if(!Phone.trim().matches("[0-9]+")){
                        System.out.println("phone "+Phone);
                        foundErr = true;
                        errors.setPhoneIsNotNumberEROR("Required typing phone is an integer!");
               }
               if(!foundErr){
                  //1.Cust goes to his/her cart plase
                  HttpSession session = request.getSession(false);
                  //2.Cust takes his/her cart
                  cartObject cart = (cartObject)session.getAttribute("CUSTCART");
                  CartDAO dao = new CartDAO();
                  BillDAO Billdao = new BillDAO();
                  ItemDAO itemDao = new ItemDAO();
                  boolean flag = false;
                  if(cart  != null){
                      Map<String, Integer> items = cart.getItems();
                          if(items != null){
                              int result = dao.insertCart(Integer.parseInt(TotalPrice), Name, Adress, Phone);
                                          if(result!=0){
                                                   List<ItemDTO> listBookInCart = new ArrayList<>();
                                                            for (String ID : items.keySet()) {
                                                                     ItemDTO dto = itemDao.getBookByID(ID, items.get(ID));
                                                                     if(dto!=null){
                                                                              flag = Billdao.insertItem(result, dto.getID(), dto.getNum());
                                                                              if(!flag){
                                                                                  break;
                                                                              }
                                                                     }
                                                           }
                                          }
                          }  
                  }
                  if(flag){
                      session.removeAttribute("CUSTCART");
                      session.removeAttribute("CHECKOUT");
                      session.removeAttribute("TOTAL_PRICE");
                      session.removeAttribute("TOTAL_NUM");
                      url = SHOPPING_PAGE_SUCCESS;
                  }
             }else{
                        request.setAttribute("CHECKOUT_ERR", errors);
                        url = CHECKOUT_PAGE;
               }
        }catch(SQLException e)  {
                 log("PaymentServlet_SQL "+e.getMessage());
        }catch(NamingException ex)  {
                  log("PaymentServlet_Naming "+ex.getMessage());
            }catch(NumberFormatException ex)  {
                  log("PaymentServlet_NumberFormat "+ex.getMessage());
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
