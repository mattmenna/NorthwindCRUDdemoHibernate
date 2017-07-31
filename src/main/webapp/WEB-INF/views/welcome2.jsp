<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 7/31/2017
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" href="<c:url value='/resources/css/creative.min.css' />" rel="stylesheet" />
    <script src="<c:url value='/resources/js/creative.min.js' />" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Insert title here</title></head>
<body>
<h1>This is a test</h1>
<a href="/getNewCust">Add new Customer</a>
<form action="searchByCity" method="get">
    <input type="text" name="city">
    <input type="submit" name="Search">
</form>

<img src="<c:url value='/resources/img/portfolio/thumbnails/1.jpg'/>" class="img-responsive" alt="">
<table border=1>
    <c:forEach var="myvar" items="${cList}">
        <tr>
            <td> ${myvar.customerId}</td>
            <td> ${myvar.companyName}</td>
            <td> <a href="delete?id=${myvar.customerId}">Delete</a> </td>
        </tr>
    </c:forEach></table>
</body>
</html>