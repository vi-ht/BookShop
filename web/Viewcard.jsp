<%-- 
    Document   : Viewcard.jsp
    Created on : Oct 19, 2020, 1:37:25 PM
    Author     : Thanh Vi
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
        <link rel="stylesheet" href="Viewcart.css">
        <style>
                 input[type=submit] {
                           width: 100%;
                           background-color: #F7819F;
                           color: white;
                           padding: 10px 10px;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                 }
                 #checkout{
                          width: 450px;
                          
                 }
                 #tb{
                           background-color:#F5A9BC;
                          border: 4px solid #F5A9BC;
                          border-radius: 5px;
                          box-sizing: border-box;
                          width: 40%;
                          display: inline-block;
                           margin: 0 30% ;
                           margin-bottom: 10px;
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
                           width: 10%;
                           height: 10px;
                  }
                  h1{
                           text-align: center;
                           font-size: 40px;
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
    <body style="background-color:#F6CED8">
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
                           <li><a href="checkout">Check Out</a></li>
                           <li><a href="viewCart" style="background-color: white; color: hotpink;">View Your Cart</a></li> 
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
                           <li><a href="checkout">Check Out</a></li> 
                           <li><a href="viewCart" style="background-color: white; color: hotpink;">View Your Cart</a></li> 
                           <li><a href="showBook" >Shopping Book</a></li>                         
                         </ul>
            </div><br>
             </c:if>
        <h1>Your cart</h1>
        <c:if test="${not empty sessionScope.CUSTCART}">
                  <c:if test="${not empty requestScope.LISTBOOKINCART}">
                           <form action="cart">
                                    <table border="1" id="tb">
                                    <thead>
                                             <tr>
                                                      <th>No.</th>
                                                      <th>Title</th>
                                                      <th>Quantity</th>
                                                      <th>Action</th>
                                             </tr>
                                    </thead>
                                    <tbody>
                                             <c:forEach var="cart" items="${requestScope.LISTBOOKINCART}" varStatus="counter">
                                                      <tr>
                                                               <td>${counter.count}</td>
                                                               <td>${cart.title}</td>
                                                               <td>${cart.num}</td>
                                                               <td><input type="checkbox" name="chkItem" value="${cart.ID}" style="background-color:#F6CED8" /></td>
                                                     </tr>
                                                     </c:forEach>    
                                                     <tr>
                                                              <td colspan="3" > <a href="showBook">Add more Item</td>
                                                              <td><input type="submit" value="Remove" name ="btAction" id="remove" style="background-color: #F7819F"/></td>
                                                    </tr>
                                                    <tr>
                                                             <td colspan="4" >
                                                                               <input type="submit" value="Check out"  name="btAction" id="checkout"/>
                                                             </td>
                                                    </tr>
                                    </tbody>
                           </table>
                           </form>
                  </c:if>
                  <c:if test="${empty requestScope.LISTBOOKINCART}">
                  <div id="norecord">
                          <h2>No book in your cart!!!!</h2><br>
                           <a href="showBook">Click here to go to buy Advanced JavaBook</a>
                  </div>
                  </c:if>
        </c:if>
        <c:if test="${empty sessionScope.CUSTCART}">
                 <div id="norecord">
                          <h2>No book in your cart!!!!</h2><br>
                           <a href="showBook">Click here to go to buy Advanced JavaBook</a>
                 </div>
                  </c:if>
    </body>
</html>
