<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panier</title>
    <jsp:include page="head.jsp"/>
    <link href="<c:url value="/css/basket.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <c:forEach items="${basket.productsAndQuantities}" var="mapValue">
        <p>
            ${mapValue.key.name}
            <img src="image/${mapValue.key.img}" style="max-height: 200px; max-width: 200px;"/>
            Prix unité : ${mapValue.key.price}
            Quantité : ${mapValue.value}
            <hr>
        </p>
    </c:forEach>


</body>
</html>
