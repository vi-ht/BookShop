<%-- 
    Document   : confirmUpdate
    Created on : Oct 14, 2020, 5:22:11 PM
    Author     : Thanh Vi
--%>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
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
                          width: 50%;
                          text-align:left;
                          display: inline-block;
                           margin: 1% 25% ;
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
                    
                   input[type=submit]{
                                     width: 100%;
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
                  a{
                           text-decoration: none;
                           color: black;
                           text-align: center;
                  }
                  a:hover {
                          color:#F7819F;
                 }
                   #b{
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
                   #nomodify{
                           background-color:#F5A9BC;
                           width: 100%;
                           padding: 12px 20px;
                           margin: 8px 0;
                           display: inline-block;
                           border: 2px solid white;
                           border-radius: 4px;
                           box-sizing: border-box;
                         }
                         text{
                                  color: white;
                         }
        </style>
    </head>
    <body style="background-color:#F6CED8">
             <c:if test="${empty sessionScope.FULLNAME}">
                      <c:redirect url="loginPage"/>
             </c:if>
             <div id="b">
                                <font style="font-weight: bold;" >Welcome, ${sessionScope.FULLNAME}</font>
                                <ul>
                                         <li><a href="logout">Logout</a></li>
                                </ul>
                       </div>
                <form action="updateSearch">
                         <h1>Confirm Update!</h1>
                         <c:if test="${requestScope.US eq sessionScope.USERNAME}">
                                  <c:if test="${requestScope.ROLE}">
                                           Username: (Not modify) <input type="text" name="cfUsername" value="${requestScope.US}"  readonly="readonly" id="nomodify"/><br>
                                           Password: <input type="text" name="cfPassword" value="${requestScope.PW}" /><text>${requestScope.ERROR}</text><br>
                                           Fullname: (Not modify) <input type="text" name="cfFullname" value="${requestScope.LN}" readonly="readonly"  id="nomodify"/><br>
                                           <input type="hidden" name="cfSearchValue" value="${requestScope.LSV}" />
                                           Role: <input type="checkbox" name="cfRole" value="ADMIN" 
                                                        <c:if test="${requestScope.ROLE}" >
                                                                 checked="checked"
                                                        </c:if>
                                                        />
                                  <br> 
                                  </c:if>
                                  <c:if test="${not requestScope.ROLE}">
                                           Username: (Not modify) <input type="text" name="cfUsername" value="${requestScope.US}"  readonly="readonly" id="nomodify"/><br>
                                           Password: <input type="text" name="cfPassword" value="${requestScope.PW}" /><text>${requestScope.ERROR}</text><br>
                                           Fullname: (Not modify) <input type="text" name="cfFullname" value="${requestScope.LN}" readonly="readonly"  id="nomodify"/><br>
                                           <input type="hidden" name="cfSearchValue" value="${requestScope.LSV}" />
                                           Role: (Not modify) <input type="checkbox" name="cfRole" value="ADMIN" onclick="return false"
                                                        <c:if test="${requestScope.ROLE}" >
                                                                 checked="checked"
                                                        </c:if>
                                                        />
                                  <br>
                                  </c:if>
                                  </c:if>
                                  <c:if test="${requestScope.US ne sessionScope.USERNAME}">
                                           Username: (Not modify) <input type="text" name="cfUsername" value="${requestScope.US}"  readonly="readonly" id="nomodify"/><br>
                                           Password: (Not modify) <input type="text" name="cfPassword" value="${requestScope.PW}" readonly="readonly" id="nomodify"/><br>
                                           Fullname: (Not modify) <input type="text" name="cfFullname" value="${requestScope.LN}" readonly="readonly"  id="nomodify"/><br>
                                           <input type="hidden" name="cfSearchValue" value="${requestScope.LSV}" />
                                           Role: <input type="checkbox" name="cfRole" value="ADMIN" 
                                                        <c:if test="${requestScope.ROLE}" >
                                                                 checked="checked"
                                                        </c:if>
                                                        />
                                  <br> 
                         </c:if>
                    <input type="submit" value="Update" name="btAction" style="background-color: #F7819F"/>
                    <c:url var="urlrewriting" value="back">
                             <c:param name="lastSearchValue" value="${requestScope.LSV}"/>
                    </c:url>
                    <a href="${urlrewriting}">Click here to return search page!!!</a>
        </form>
    </body>
</html>
