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
          <th>Attribute 1</th>
          <th>Attribute 2</th>
          <th>Attribute 3</th>
          <th>Attribute 4</th>
          <th>Attribute 5</th>
          <th>Delete</th>       
       </tr>
       <c:forEach items="${credentialList}" var="credentialResult" >
          <tr>
             <td>${credentialResult.attr1Key}: ${credentialResult.attr1Value}</td>
             <td>${credentialResult.attr2Key}: ${credentialResult.attr2Value}</td>
             <td>${credentialResult.attr3Key}: ${credentialResult.attr3Value}</td>
             <td>${credentialResult.attr4Key}: ${credentialResult.attr4Value}</td>
             <td>${credentialResult.attr5Key}: ${credentialResult.attr5Value}</td>
             <td>
                <a href="delete?referent=${credentialResult.referent}">Delete</a>
             </td>
          </tr>
          
       </c:forEach>
    </table>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>	