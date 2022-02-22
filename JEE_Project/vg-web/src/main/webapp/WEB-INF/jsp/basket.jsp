<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panier</title>
    <jsp:include page="component/header.jsp"/>
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
    <hr>
    <c:choose>
        <c:when test="${totalAmount == -1}">
            <p>Votre panier est vide</p>
        </c:when>
        <c:otherwise>
            <p>Total : ${totalAmount} €</p>
            <button onclick="buy()">Acheter</button>
        </c:otherwise>
    </c:choose>


</body>
</html>

<script>
    function buy(){
       window.location = "${prefix}/payment";
    }
</script>