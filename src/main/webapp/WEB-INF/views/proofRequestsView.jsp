<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Proof Requests</title>
     <link rel="stylesheet" href="main.css">
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>    
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Proof Requests</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Role</th>
          <th>Connection ID</th>
          <th>Create_at</th>
          <th>State</th>
          <th>Presentation Exchange ID</th>
          <th>Delete</th>       
       </tr>
       <c:forEach items="${proofExchangeList}" var="proofExchangeList" >
          <tr>
             <td>${proofExchangeList.role}</td>
             <td>${proofExchangeList.connection_id}</td>
             <td>${proofExchangeList.create_at}</td>
             <td>${proofExchangeList.state}</td>
             <td>${proofExchangeList.presentationExchangeId}</td>
             <td>
                <a href="deleteRequest?requestId=${proofExchangeList.presentationExchangeId}">Delete</a>
             </td>
          </tr>
          
       </c:forEach>
    </table>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>	