<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2015/6/7
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="timeNow" class="java.util.Date"/>
<%@ page session="true" %>
<html>
<head>
    <title>developerWorks Tomcat Tutorial</title>
    <link rel=stylesheet type="text/css" href="specials.css">
</head>
<body>
<table width="600">
    <tr>
        <td class="mainHead" colspan="2">
            Today's specials for
            <fmt:formatDate value="${timeNow}" type="date"
                            dateStyle="long"/>
        </td>
    </tr>
    <tr>
        <th>Specialty</th>
        <th>Price</th>
    </tr>
    <c:forEach var="special" items="${specials}">
        <tr>
            <td>${special.menuItem}</td>
            <td>\$${special.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
