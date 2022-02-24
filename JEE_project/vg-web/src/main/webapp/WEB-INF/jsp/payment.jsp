<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Validation de la commande</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/payment.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <section class="center">
        <h1>Informations de livraison :</h1>
        <form method="POST" action="/basket/confirm">
            <table>
                <tr><th>Choisissez le nom du destinataire</th></tr>
                <tr>
                    <td>
                        <input type="radio" class="gray" name="selectedName" id="currentName" value="current" checked>
                        <label for="currentName">${user.name}</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" class="gray" name="selectedName" id="otherName" value="other">
                        <label for="otherName">Autre : <input type="text" name="otherName"></label>
                    </td>
                </tr>
                <tr><td><br></td></tr>
                <tr><th>Choisissez l'adresse de livraison</th></tr>
                <tr>
                    <c:choose>
                        <c:when test="${not empty user.address}">
                            <td>
                                <input type="radio" class="gray" name="selectedAddress" id="currentAddress" value="current" checked>
                                <label for="currentAddress">${user.address}</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="radio" class="gray" name="selectedAddress" id="otherAddress" value="other">
                                <label for="otherAddress">Autre : <input type="text" name="otherAddress"></label>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <label>Adresse : <input name="otherAddress" placeholder="N°, Rue, Ville" required/></label>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>
            <br><br>
            <input type="submit" class="btn big" value="Confirmer">
        </form>
        <a href="/basket"><button class="big">Retour</button></a>
    </section>
</body>
</html>
