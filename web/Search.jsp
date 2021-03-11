<%-- 
    Document   : Search
    Created on : Oct 12, 2020, 12:50:43 PM
    Author     : Thanh Vi
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ShowResult</title>
        <style>
                 body{
                          background-color:#F6CED8;
                 }
                 #form1 {
                          background-color:#F5A9BC;
                          border: 5px solid #F5A9BC;
                          border-radius: 5px;
                           box-sizing: border-box;
                          padding: 40px 40px;
                          width: 60%;
                          text-align:left;
                          display: inline-block;
                           margin: 4% 20% ;
                           margin-bottom: 20px;
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
                  input[type=submit], #delete {
                           width: 100%;
                           background-color: #F7819F;
                           color: white;
                           padding: 8px 20px;
                           margin: 8px 0;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                  }
                  #search{
                           width: 100%;
                           background-color: #F7819F;
                           color: white;
                           padding: 14px 20px;
                           margin: 8px 0;
                           border: none;
                           border-radius: 4px;
                           cursor: pointer;
                  }
                  h1{
                           text-align:center;
                           font-size: 40px;
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
                 #notfound{
                          background-color: #F5A9BC;
                           display: inline-block;
                           padding: 20px 20px;
                           box-sizing: border-box;
                           margin: 15% 20% ;
                           width: 60%;
                           text-align:center;
                           border: 5px solid #F5A9BC;
                          border-radius: 5px;
                 }
                 #logout{
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
                  font{
                           float: left;
                           color: white;
                           padding: 10px 10px;
                           margin: 1px 1px;
                  }
                  #tb{
                           background-color:#F5A9BC;
                          border: 4px solid #F5A9BC;
                          border-radius: 5px;
                           box-sizing: border-box;
                          width: 60%;
                          display: inline-block;
                           margin: 0 20% ;
                           padding: 10px;
                  }
                  
                  #tb td, #tb th {
                           border: 1px solid #ddd;
                           margin: 5px 5px;
                           text-align: center;
                  }
                  #tb th {
                           text-align: center;
                           padding: 10px 10px;
                           background-color: #F7819F;
                           color: white;
                           width: 10%  
                  }
                  #tb td {
                           height: 40px;
                  }
                  #delete, #update{
                           width: 100px;
                  }
                  #pass{
                           width: 230px;
                           max-width: 250px;
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
                 a{
                          color: white;
                 }

</style>
    </head>
    <body style="background-color:#F6CED8">
             <c:if test="${empty sessionScope.FULLNAME}">
                      <c:redirect url="loginPage"/>
             </c:if>
              <c:if test="${not empty sessionScope.FULLNAME}">
                       <div id="b">
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
                                         <li><a href="showBook">Shopping Book</a></li> 
                                         <li><a href="searchPage" style="background-color: white; color: hotpink;">Search</a></li> 
                                </ul>
                       </div><br>
                       <form action="search" id="form1">
                                <h1>Search Page</h1>
                                Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br>
                                <input type="submit" value="search" name="btAction" id="search" /><br>
                       </form><br>
                       <c:set var="searchValue" value="${param.txtSearchValue}"/>
                       <c:if test="${not empty searchValue}">
                                <c:set var = "resutl" value="${requestScope.SEARCHRESULT}"/>
                                <c:if test="${not empty resutl}">
                                         <c:if test="${sessionScope.ROLE_OF_USER}">
                                                  <table border="1" id="tb">
                                                           <thead>
                                                                    <tr>
                                                                             <th>No.</th>
                                                                             <th>Username</th>
                                                                             <th>Password</th>
                                                                             <th>Full Name</th>
                                                                             <th>Role</th>
                                                                             <th>Delete</th>
                                                                             <th>Update</th>
                                                                    </tr>
                                                           </thead>
                                                           <tbody>
                                                                    <c:forEach var="dto" items="${resutl}" varStatus="counter">
                                                                    <form action="confirm">
                                                                             <tr>
                                                                                      <td>${counter.count}</td>
                                                                                      <td>
                                                                                               ${dto.username}
                                                                                               <input type="hidden" name="txtUsename" value="${dto.username}" />
                                                                                      </td>
                                                                                      <td>
                                                                                               ${dto.password}
                                                                                               <input type="hidden" name="txtPassword" value="${dto.password}" style="background-color:#F6CED8"  id="pass" />
                                                                                      </td>
                                                                                      <td>
                                                                                               ${dto.fullname}
                                                                                               <input type="hidden" name="txtLastname" value="${dto.fullname}" /></td>
                                                                                      <td>
                                                                                               <input type="checkbox" name="chkAdmin" value="ADMIN" style="background-color:#F6CED8" onclick="return false"
                                                                                                      <c:if test="${dto.role}">
                                                                                                               checked="checked"
                                                                                                      </c:if> 
                                                                                                      />
                                                                                      </td>
                                                                                      <td>
                                                                                               <c:url var="urlrewriting" value="removeSearch">
                                                                                                        <c:param name="pk" value="${dto.username}"/>
                                                                                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                                                                               </c:url>
                                                                                               <c:if test="${dto.username eq sessionScope.USERNAME}">
                              
                                                                                               </c:if>
                                                                                               <c:if test="${dto.username ne sessionScope.USERNAME}">
                                                                                                        <c:if test="${not dto.role}">
                                                                                                                     <a href="${urlrewriting}">Delete</a>
                                                                                                        </c:if>
                                                                                               </c:if>
                                                                                      </td>       
                                                                                      <td>
                                                                                               <c:if test="${dto.username eq sessionScope.USERNAME}">
                                                                                                        <input type="submit" value="Update" name="btAction" style="background-color: #F7819F"  id="update"/>
                                                                                               </c:if>
                                                                                               <c:if test="${dto.username ne sessionScope.USERNAME}">
                                                                                                        <c:if test="${dto.role}">
                                                                                                                  <input type="hidden" value="Update" name="btAction" style="background-color: #F7819F"  id="update"/>
                                                                                                        </c:if>
                                                                                                        <c:if test="${not dto.role}">
                                                                                                                    <input type="submit" value="Update" name="btAction" style="background-color: #F7819F"  id="update"/>
                                                                                                        </c:if>
                                                                                               </c:if>
                                                                                               <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                                                                      </td>
                                                                             </tr>
                                                                    </form>
                                                           </c:forEach>
                                                  </tbody>
                                         </table>
                                         </c:if>
                                         <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                  <table border="1" id="tb">
                                                           <thead>
                                                                    <tr>
                                                                             <th>No.</th>
                                                                             <th>Username</th>
                                                                             <th>Password</th>
                                                                             <th>Full Name</th>
                                                                             <th>Role</th>
                                                                             <th>Update</th>
                                                                    </tr>
                                                           </thead>
                                                           <tbody>
                                                                    <c:forEach var="dto" items="${resutl}" varStatus="counter">
                                                                    <form action="confirm">

                                                                             <tr>
                                                                                      <td>${counter.count}</td>
                                                                                      <td>
                                                                                               ${dto.username}
                                                                                               <input type="hidden" name="txtUsename" value="${dto.username}" />
                                                                                      </td>
                                                                                      <td>
                                                                                               ${dto.password}
                                                                                               <input type="hidden" name="txtPassword" value="${dto.password}" style="background-color:#F6CED8"  id="pass" />
                                                                                      </td>
                                                                                      <td>
                                                                                               ${dto.fullname}
                                                                                               <input type="hidden" name="txtLastname" value="${dto.fullname}" /></td>
                                                                                      <td>
                                                                                               <input type="checkbox" name="chkAdmin" value="ADMIN" style="background-color:#F6CED8" onclick="return false"
                                                                                                      <c:if test="${dto.role}">
                                                                                                               checked="checked"
                                                                                                      </c:if> 
                                                                                                      />
                                                                                      </td>   
                                                                                      <td>
                                                                                               <c:if test="${dto.username eq sessionScope.USERNAME}">
                                                                                                        <input type="submit" value="Update" name="btAction" style="background-color: #F7819F"  id="update"/>
                                                                                               </c:if>
                                                                                               <c:if test="${dto.username ne sessionScope.USERNAME}">
                                                                                                        <input type="hidden" value="Update" name="btAction" style="background-color: #F7819F"  id="update"/>
                                                                                               </c:if>
                                                                                               <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                                                                      </td>
                                                                             </tr>
                                                                    </form>
                                                           </c:forEach>
                                                  </tbody>
                                         </table>
                                         </c:if>     
                       </c:if>
                       <c:if test="${empty resutl}">
                                <h2>No record is matched!! </h2>
                       </c:if>
              </c:if>
             </c:if>
    </body>
</html>
