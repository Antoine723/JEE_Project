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
                <h3>Votre panier est vide</h3>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Produit</th>
                        <th>Prix unitaire</th>
                        <th>Quantité</th>
                        <th>Sous-total</th>
                        <th>Ajuster</th>
                        <th>Retirer</th>
                    </tr>
                    <c:forEach items="${qtyByProduct}" var="mapValue" varStatus="loop">
                        <tr>
                            <td><a href="/product/${mapValue.key.id}">${mapValue.key.name}</a></td>
                            <td>${mapValue.key.price}€</td>
                            <td>
                                ${mapValue.value}
                            </td>
                            <td>${mapValue.key.price*mapValue.value}€</td>
                            <td>
                                <form method="POST" action="/basket/update/${mapValue.key.id}">
                                    <input type="hidden" name="redirect" value="/basket">
                                    <label><input type="number" name="quantity" min="-${mapValue.value-1}" max="${mapValue.key.quantity}" value="0"/></label>
                                    <input type="submit" class="btn bolder" value="✓">
                                </form>
                            </td>
                            <td>
                                <a href="/basket/remove/${mapValue.key.id}"><button>🗙</button></a>
                            </td>
                        </tr>
                        <c:if test="${!loop.last}"><hr></c:if>
                    </c:forEach>
                </table>
                <br>
                <p><span class="bold">Total :</span> ${totalAmount} €</p>
                <a href="/payment"><button class="big">Acheter</button></a>
            </c:otherwise>
        </c:choose>
    </section>
</body>
</html>