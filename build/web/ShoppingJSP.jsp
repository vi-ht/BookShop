<%-- 
    Document   : ShoppingJSP
    Created on : Oct 19, 2020, 4:41:59 PM
    Author     : Thanh Vi
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <style>
                 #tb{
                           background-color:#F5A9BC;
                          border: 4px solid #F5A9BC;
                          border-radius: 5px;
                           box-sizing: border-box;
                          width: 60%;
                          display: inline-block;
                           margin: 0 20% ;
                           margin-bottom: 10px;
                           padding: 10px;
                  }
                  
                  #tb td, #tb th {
                           border: 1px solid #ddd;
                           margin: 5px 5px;
                            padding: 10px 20px;
                           text-align: center;
                           
                  }
                  #tb th {
                           text-align: center;
                           padding: 10px 20px;
                           background-color: #F7819F;
                           color: white;
                           width: 15%;
                          
                  }
                  input[type=submit] {
                           width: 100%;
                           margin: 0px 5px;
                           background-color: #F7819F;
                           color: white;
                           padding: 10px 20px;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                  }
                  h1{
                           text-align: center;
                           font-size: 40px;
                  }
                  p{
                           float: left;
                           margin-left: 20%;
                           font-size: 20px;
                  }
                  #view{
                           width: 60%;
                           margin: 0 20%;
                           
                  }
                   #login{
                          width: 100px;
                           background-color: #F7819F;
                           color: white;
                           padding: 5px 5px;
                           margin: 1px 1px;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                           float: right;
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
                  h2{
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
                           <li><a href="viewCart">View Your Cart</a></li> 
                           <li><a href="showBook" style="background-color: white; color: hotpink;">Shopping Book</a></li>
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
                           <li><a href="viewCart">View Your Cart</a></li> 
                           <li><a href="showBook" style="background-color: white; color: hotpink;">Shopping book</a></li>
                         </ul>
            </div><br>
             </c:if>
         <h1>Shopping Cart</h1>
         <p>Please choose book:<p>
                  <c:if test="${ not empty requestScope.LISTBOOK}">
                  <table border="1" id="tb">
                           <thead>
                                    <tr>
                                             <th>No.</th>
                                             <th>Title</th>
                                             <th>Price</th>
                                             <th>Add to cart</th>
                                    </tr>
                           </thead>
                           <tbody>
                                    <c:forEach var="ItemDTO" items="${requestScope.LISTBOOK}" varStatus="counter">
                                             <form action="addToCart">
                                             <tr>
                                                      <td>${counter.count}</td>
                                                      <td>
                                                               ${ItemDTO.title}
                                                               <input type="hidden" name="txtTitle" value="${ItemDTO.title}" />
                                                      </td>
                                                      <td>
                                                               ${ItemDTO.price}
                                                               <input type="hidden" name="txtPrice" value="${ItemDTO.price}" />
                                                               <input type="hidden" name="txtID" value="${ItemDTO.ID}" />
                                                      </td>
                                                      <td>
                                                               <input type="submit" value="Add to cart" name ="btAction"  style="background-color: #F7819F"/>
                                                      </td>
                                             </tr>
                                              </form>
                                    </c:forEach>   
                           </tbody>
                  </table>
                  <form  action="viewCart">
                           <input type="submit" value="View your card" name ="btAction"  style="background-color: #F7819F" id="view"/>
                  </form>
         </c:if>
                  <c:if test="${empty requestScope.LISTBOOK}">
                  <h2>No book to show!! </h2>
         </c:if>
    </body>
</html>
