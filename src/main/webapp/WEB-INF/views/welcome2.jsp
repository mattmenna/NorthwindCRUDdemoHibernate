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
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
    <title>Insert title here</title></head>
<table border=1>
    <c:forEach var="myvar" items="${cList}">
    <tr>
        <td> ${myvar.customerId}</td>
        <td> ${myvar.companyName}</td>
    </tr>
</c:forEach></table>
</body> </html>