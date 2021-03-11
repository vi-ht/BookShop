<%-- 
    Document   : createNewAccount
    Created on : Nov 2, 2020, 12:58:25 PM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <title>Create New Account</title>
                  <style>
                            body{
                                    background-color:#F6CED8;
                           }
                           form {
                                    background-color:#F5A9BC;
                                    border: 5px solid #F5A9BC;
                                    border-radius: 5px;
                                     box-sizing: border-box;
                                    padding: 50px;
                                    width: 40%;
                                    text-align:left;
                                    display: inline-block;
                                     margin: 5% 30% ;
                           }
                           input[type=text], select {
                                     width: 100%;
                                     padding: 12px 20px;
                                     margin: 8px 0;
                                     display: inline-block;
                                     border: 1px solid #ccc;
                                     border-radius: 4px;
                                     box-sizing: border-box;
                                   }
                           input[type=password], select {
                                     width: 100%;
                                     padding: 12px 20px;
                                     margin: 8px 0;
                                     display: inline-block;
                                     border: 1px solid #ccc;
                                     border-radius: 4px;
                                     box-sizing: border-box;
                                   }

                            input[type=submit], input[type=reset]{
                                     width: 49%;
                                     background-color: #F7819F;
                                     color: white;
                                     padding: 14px 20px;
                                     margin: 12px 0;
                                     border: none;
                                     border-radius: 4px;
                                     cursor: pointer;
                            }
                            h1{
                                     text-align:center;
                                     font-size: 40px;
                            }
                            #a{
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
                                    padding-bottom: 10px;
                                    display: inline;
                           }
                  </style>
         </head>
         <body>
                  <div id="a">   
                           <ul>
                                    <li><a href="createPage" style="background-color: white; color: hotpink;">Sign up</a></li>
                                     <li><a href="loginPage" >Login</a></li>
                                     <li><a href="checkout">Check Out</a></li>
                                     <li><a href="viewCart">View Your Cart</a></li> 
                                     <li><a href="showBook">Shopping Book</a></li>
                            </ul>
                   </div>
                  <c:set var="erorrs" value="${requestScope.CREATE_ERR}"/>
                  <form action="create" method="POST">
                           <h1>Create new acount</h1>
                           Username: (6-30 char)<input type="text" name="txtUsername" value="${param.txtUsername}"/><br>
                           <c:if test="${not empty erorrs.usernameLenghERROR}">
                                    <font color ="white">
                                             ${erorrs.usernameLenghERROR}
                                    </font><br>
                           </c:if>
                           Password: (6-20 char)<input type="password" name="txtPass" value="" /><br>
                           <c:if test="${not empty erorrs.passLenghERROR}">
                                    <font color ="white">
                                             ${erorrs.passLenghERROR}
                                    </font><br>
                           </c:if>
                           Confirm: <input type="password" name="txtConfirm" value="" /><br>
                           <c:if test="${not empty erorrs.confirmNotMatchERROR}">
                                    <font color ="white">
                                             ${erorrs.confirmNotMatchERROR}
                                    </font><br>
                           </c:if>
                           Fullname: <input type="text" name="txtFullname" value="${param.txtFullname}"/><br>
                           <c:if test="${not empty erorrs.fullnameLenghERROR}">
                                    <font color ="white">
                                             ${erorrs.fullnameLenghERROR}
                                    </font><br>
                           </c:if>
                           <input type="submit" value="Create new account" name ="btAction" />
                           <input type="reset" value="reset" /><br>
                           <c:if test="${not empty erorrs.usernameIsExistERROR}">
                                    <font color ="white">
                                             ${erorrs.usernameIsExistERROR}
                                    </font><br>
                           </c:if>
                  </form>
         </body>
</html>
