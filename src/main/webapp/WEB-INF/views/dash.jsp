<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 
<p>Item  : ${item}</p>
<p>Usage : ${usage}</p>
-->
 <c:choose>
     <c:when test="${empty items}">
       Table is empty.
     </c:when>
     <c:otherwise>
<table>
       <thead>
         <tr>
          <th> Item ID </th>
          <th> Item Name </th>
          <th> Usage </th>
         </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
        <tr>
          <td> <c:out value="${item[0]}"/> </td>
          <td> <c:out value="${item[1]}"/> </td>
          <td> <c:out value="${item[2]}"/> </td>
        </tr>
        </c:forEach>
       </tbody>
     </table>
     </c:otherwise>
   </c:choose>
</body>
</html>