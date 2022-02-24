<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Panier</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/basket.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <section class="center">
        <c:choose>
            <c:when test="${qtyByProduct.isEmpty()}">
                <p>Votre panier est vide</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Produit</th>
                        <th>Prix unitaire</th>
                        <th>Quantité</th>
                        <th>Sous-total</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${qtyByProduct}" var="mapValue" varStatus="loop">
                        <tr>
                            <td><a href="/product/${mapValue.key.id}">${mapValue.key.name}</a></td>
                            <td>${mapValue.key.price}€</td>
                            <td>${mapValue.value}</td>
                            <td>${mapValue.key.price*mapValue.value}€</td>
                            <td>
                                <button>-</button>
                                <button>+</button>
                                <button>x</button>
                            </td>
                        </tr>
                        <c:if test="${!loop.last}"><hr></c:if>
                    </c:forEach>
                </table>
                <hr>
                <p><span class="bold">Total :</span> ${totalAmount} €</p>
                <a href="/payment"><button>Acheter</button></a>
            </c:otherwise>
        </c:choose>
    </section>
</body>
</html>