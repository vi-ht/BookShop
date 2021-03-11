<%-- 
    Document   : CheckOut
    Created on : Oct 21, 2020, 4:17:30 PM
    Author     : Thanh Vi
--%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
        <style>
                 body{
                           background-color:#F6CED8;
                 }
                 input[type=submit] {
                           width: 100%;
                           background-color: #F7819F;
                           color: white;
                           padding: 10px 10px;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                 }
                 input[type=text], select {
                           width: 100%;
                           padding: 10px 15px;
                           margin: 8px 0;
                           display: inline-block;
                           border: 1px solid #ccc;
                           border-radius: 4px;
                           box-sizing: border-box;
                         }
                 #pay{
                          width: 520px;
                 }
                 #tb{
                           background-color:#F5A9BC;
                          border: 4px solid #F5A9BC;
                          border-radius: 5px;
                          box-sizing: border-box;
                          width: 100%;
                          display: inline-block;
                           padding: 8px;
                  }
                  
                  #tb td, #tb th {
                           border: 1px solid #ddd;
                           margin: 5px 5px;
                            padding: 10px 10px;
                           text-align: center;
                           
                  }
                  #tb th {
                           text-align: center;
                           background-color: #F7819F;
                           color: white;
                           width: 15%;
                           height: 10px;
                          
                  }
                  h1{
                           text-align: center;
                           font-size: 40px;
                  }
                  #form1 {
                          background-color:#F5A9BC;
                          border: 5px solid #F5A9BC;
                          border-radius: 5px;
                           box-sizing: border-box;
                          padding: 20px 20px;
                          width: 50%;
                          text-align:left;
                          display: inline-block;
                           margin: 0 25% ;
                           margin-bottom: 20px;
                 }
                 #total{
                          background-color: white;
                 }
                #in, #out{
                          background-color: #F5A9BC;
                           display: inline-block;
                           margin: 10px 10px 10px 10x;
                           padding: 5px 5px;
                           box-sizing: border-box;
                           width: 100%;
                 }
                 ul {
                          list-style-type: none;
                          margin: 0;
                          padding: 0;
                          overflow: hidden;
                          background-color: #F5A9BC;
                 }

                 li a {
                  display: block;
                  color: white;
                  text-align: center;
                  padding: 10px 10px;
                  text-decoration: none;
                }
                 li {
                          float: right;
                 }

                 li a:hover {
                          background-color: #F7819F;
                 }
                 font{
                           float: left;
                           color: white;
                           padding: 10px 10px;
                           margin: 1px 1px;
                  }
                  #norecord{
                          background-color: #F5A9BC;
                           display: inline-block;
                           padding: 20px 20px;
                           box-sizing: border-box;
                           margin: 0 20% ;
                           width: 60%;
                           text-align:center;
                           border: 5px solid #F5A9BC;
                          border-radius: 5px;
                 }
        </style>
    </head>
    <body>
             <c:if test="${sessionScope.LOGOUT}">
                      
             </c:if>
             <c:if test="${not empty sessionScope.FULLNAME}">
                      <div id="in">
                               <c:if test="${sessionScope.ROLE_OF_USER}">
                                         <font style="font-weight: bold;" >Welcome, ${sessionScope.FULLNAME} (Admin)</font>
                                </c:if>
                                <c:if test="${not sessionScope.ROLE_OF_USER}">
                                         <font style="font-weight: bold;" >Welcome, ${sessionScope.FULLNAME}</font>
                                </c:if>
                                <ul>
                                    <li><a href="logout">Logout</a></li>
                                    <li><a href="checkout" style="background-color: white; color: hotpink;">Check Out</a></li>
                                    <li><a href="viewCart">View Your Cart</a></li> 
                                    <li><a href="showBook" >Shopping Book</a></li>
                                    <li><a href="searchPage">Search</a></li> 
                                </ul>
                               </div><br>
             </c:if>
             <c:if test="${empty sessionScope.FULLNAME}">
             <div id="out">
                  <ul>
                           <li><a href="createPage">Sign up</a></li>
                           <li><a href="loginPage">Login</a></li>
                           <li><a href="checkout" style="background-color: white; color: hotpink;">Check Out</a></li> 
                           <li><a href="viewCart">View Your Cart</a></li> 
                           <li><a href="showBook" >Shopping Book</a></li>
                         </ul>
            </div><br>
             </c:if>
       <h1>Check Out!</h1>
       <c:set var="erorrs" value="${requestScope.CHECKOUT_ERR}"/>
        <form action="pay" id="form1">
                 <p><b>Your Adress:</b></p>
                 Name : <input type="text" name="txtCustName" value="${param.txtCustName}" />
                  <c:if test="${not empty erorrs.nameLengthERROR}">
                                    <font color ="red">
                                             ${erorrs. nameLengthERROR}
                                    </font><br>
                 </c:if>
                  Adress:<br> <input type="text" name="txtAddress" value="${param.txtAddress}" />
                  <c:if test="${not empty erorrs.adressLengthERROR}">
                                    <font color ="red">
                                             ${erorrs. adressLengthERROR}
                                    </font><br>
                 </c:if>
                 Phone:<br> <input type="text" name="txtPhone" value="${param.txtPhone}" />
                  <c:if test="${not empty erorrs.phoneIsNotNumberEROR}">
                                    <font color ="red">
                                             ${erorrs.phoneIsNotNumberEROR}
                                    </font><br>
                 </c:if>
                 <c:if test="${not empty erorrs.phoneLengthERROR}">
                                    <font color ="red">
                                             ${erorrs.phoneLengthERROR}
                                    </font><br>
                 </c:if>
        <p><b>Your Oder:</b></p>
        <c:if test="${not empty sessionScope.CUSTCART}">
                 <c:if test="${not empty sessionScope.CHECKOUT}">
                          <table border="1" id="tb">
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Title</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                             <c:set var="totalPrice" value="${0}"/>
                                             <c:set var="totalNumber" value="${0}"/>
                                             <c:forEach var="cart" items="${sessionScope.CHECKOUT}" varStatus="counter">
                                                      <tr>
                                                               <td>
                                                                   ${counter.count}
                                                               </td>
                                                               <td>
                                                                   ${cart.title}
                                                               </td>
                                                               <td>
                                                                   ${cart.price}
                                                               </td>
                                                               <td>
                                                                    ${cart.num}
                                                               </td>
                                                               <td>
                                                                  ${cart.num * cart.price}
                                                               </td>
                                                      </tr>
                                             </c:forEach>
                                                       <tr>
                                                               <td colspan="3"> total: </td>
                                                               <td colspan="1"> ${sessionScope.TOTAL_NUM} </td>
                                                               <td id="total">
                                                                        ${sessionScope.TOTAL_PRICE}
                                                                        <input type="hidden" name="txtTotal" value="${sessionScope.TOTAL_PRICE}"/>
                                                               </td>
                                             </tr>
                                             <tr>
                                                      <td colspan="5" ><input type="submit" value="payment" name="btAction" id="pay"/></td>
                                             </tr>
                                     </tbody>
                                         </form>
                                </table>
                </c:if>
                 <c:if test="${empty sessionScope.CHECKOUT}">
                  <div id="norecord">
                          <h2>No book to check out!!!!</h2><br>
                           <a href="ShoppingServlet" id="login_Error">Add more book ro check out!!</a>
                  </div>
                  </c:if>
        </c:if>
        <c:if test="${empty sessionScope.CUSTCART}">
                 <div id="norecord">
                          <h2>No book to check out!!!!</h2><br>
                          <a href="ShoppingServlet" id="login_Error">Add more book ro check out!!</a>
                 </div>
       </c:if>
    </body>
</html>
