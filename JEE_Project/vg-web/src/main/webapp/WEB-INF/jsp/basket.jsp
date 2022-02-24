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
            <c:when test="${qtyByConsoleByProduct.isEmpty()}">
                <h3>Votre panier est vide</h3>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Produit</th>
                        <th>Console</th>
                        <th>Prix unitaire</th>
                        <th>Quantité</th>
                        <th>Sous-total</th>
                        <th>Ajuster</th>
                        <th>Retirer</th>
                    </tr>
                    <c:forEach items="${qtyByConsoleByProduct}" var="mapValue">
                        <c:forEach items="${mapValue.value}" var="subMapValue">
                        <tr>
                            <td>
                                <a href="/product/${mapValue.key.id}<c:if test="${mapValue.key.id != subMapValue.key.id}">/${subMapValue.key.name}</c:if>">
                                        ${mapValue.key.name}
                                </a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${mapValue.key.id != subMapValue.key.id}">
                                        <a href="/product/${subMapValue.key.id}">${subMapValue.key.name}</a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${mapValue.key.price}€</td>
                            <td>${subMapValue.value}</td>
                            <td>${mapValue.key.price*subMapValue.value}€</td>
                            <td>
                                <form method="POST" action="/basket/update/${mapValue.key.id}/${subMapValue.key.id}">
                                    <input type="hidden" name="redirect" value="/basket">
                                    <label><input type="number" name="quantity" min="-${subMapValue.value-1}" max="${mapValue.key.quantity}" value="0"/></label>
                                    <input type="submit" class="btn bolder" value="✓">
                                </form>
                            </td>
                            <td>
                                <a href="/basket/remove/${mapValue.key.id}/${subMapValue.key.id}"><button>🗙</button></a>
                            </td>
                        </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
                <br>
                <p><span class="bold">Total :</span> ${totalAmount} €</p>
                <a href="/basket/payment"><button class="big">Acheter</button></a>
            </c:otherwise>
        </c:choose>
    </section>
</body>
</html>