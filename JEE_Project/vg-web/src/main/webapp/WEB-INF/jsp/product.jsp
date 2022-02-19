<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 19/02/2022
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body>
    <div class="card">
        <c:choose>
            <c:when test="${not empty consoleGameName}">
                <img src="/image/${product.img}_${consoleGameName}" style="max-height: 400px; max-width: 400px;"/>
            </c:when>
            <c:otherwise>
                <img src="/image/${product.img}" style="max-height: 400px; max-width: 400px;"/>
            </c:otherwise>
        </c:choose>
        <p>Nom : ${product.name}</p>
        <p>Note : ${product.rating}/5</p>
        <p>Date de sortie : ${product.releaseYear}</p>
        <p>Prix : ${product.price}</p>
    </div>
</body>
</html>
