<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Panier</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/basket.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <section>
        <input type="hidden" id="urlPrefix" value="${prefix}">
        <c:forEach items="${basket.productsAndQuantities}" var="mapValue" varStatus="loop">
            <div>
                <h3>${mapValue.key.name}</h3>
                <img src="image/${mapValue.key.img}" alt="product_img"/>
                <p>Prix unitaire : ${mapValue.key.price}€</p>
                <p>Quantité : ${mapValue.value}</p>
            </div>
            <c:if test="${!loop.last}"><hr></c:if>
        </c:forEach>
        <hr>
        <c:choose>
            <c:when test="${totalAmount == -1}">
                <p>Votre panier est vide</p>
            </c:when>
            <c:otherwise>
                <p>Total : ${totalAmount} €</p>
                <a href="/payment"><button>Acheter</button></a>
            </c:otherwise>
        </c:choose>
    </section>
</body>
</html>