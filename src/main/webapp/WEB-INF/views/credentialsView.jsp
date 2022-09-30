<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Credentials</title>
     <link rel="stylesheet" href="main.css">
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>    
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Credentials</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Name</th>
          <th>Age</th>
          <th>Date</th>
          <th>Degree</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${credentialList}" var="credentialResult" >
          <tr>
             <td>${credentialResult.name}</td>
             <td>${credentialResult.age}</td>
             <td>${credentialResult.date}</td>
             <td>${credentialResult.degree}</td>
             <td>
                <a href="delete?referent=${credentialResult.referent}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>	