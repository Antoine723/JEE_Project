<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Validation de la commande</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/payment.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/payment.js"/>"></script>
</head>
<body>
    <section>
        <h1>Informations de livraison :</h1>
        <form>
            <label id="labelName">Nom : ${user.name}</label>
            <br>
            <c:choose>
                <c:when test="${not empty user.address}">
                    <label id="labelAddress" for="inputAddress">Adresse : ${user.address}</label>
                    <input id="inputAddress" placeholder="${user.address}" class="d-none"/>
                    <button id="editAddress">Editer</button>
                    <button id="saveAddress" class="d-none">Sauvegarder</button>
                </c:when>
                <c:otherwise>
                    <label id="labelAddress" for="inputAddress">Adresse : N/A</label>
                    <input id="inputAddress" placeholder="N°, Rue, Ville" class="d-none"/>
                    <button id="editAddress">Editer</button>
                    <button id="saveAddress" class="d-none">Sauvegarder</button>
                </c:otherwise>
            </c:choose>
        </form>
    </section>
</body>
</html>
