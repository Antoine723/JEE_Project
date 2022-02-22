<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Validation de la commande</title>
    <jsp:include page="head.jsp"/>
    <link href="<c:url value="/css/payment.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <p>
    <h1>Informations de livraison :</h1>
    <form>
        <label id="labelName">Nom : ${user.name}</label>
        </br>
        <c:choose>
            <c:when test="${not empty user.address}">
                <label id="labelAddress">Adresse : ${user.address}</label>
                <input id="inputAddress" placeholder="${user.address}" class="d-none"/>
                <button id="editAddress" onclick="editAddress()">Editer</button>
                <button id="saveAddress" onclick="saveAddress()" class="d-none">Sauvegarder</button>
            </c:when>
            <c:otherwise>
                <label id="labelAddress">Adresse : N/A</label>
                <input id="inputAddress" placeholder="N°, Rue, Ville" class="d-none"/>
                <button id="editAddress" onclick="editAddress()">Editer</button>
                <button id="saveAddress" onclick="saveAddress()" class="d-none">Sauvegarder</button>
            </c:otherwise>
        </c:choose>
    </form>
    </p>

</body>
</html>

<script>
    function editAddress(){
        $("#labelAddress").addClass("d-none");
        $("#inputAddress").removeClass("d-none");
        $("#editAddress").addClass("d-none");
        $("#saveAddress").removeClass("d-none");
    }

    function saveAddress(){
        $("#labelAddress").removeClass("d-none");
        $("#inputAddress").addClass("d-none");
        $("#editAddress").removeClass("d-none");
        $("#saveAddress").addClass("d-none");
    }
</script>
