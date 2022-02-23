<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <link href="<c:url value="/css/component/header.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src='<c:url value="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.js"/>'></script>
    <%
        try {
            long userId = (long) request.getSession().getAttribute("userID");
            pageContext.setAttribute("userId", userId);
        } catch (NullPointerException | NumberFormatException ignore) {
            pageContext.setAttribute("userId", "-1");
        }
    %>
</head>
<header>
    <input type="hidden" id="gotUser" value="${userId!='-1'}">

    <div>logo ?</div>

    <a href="/">Accueil</a>
    <a href="/products">Produits</a>
    <c:choose>
        <c:when test="${userId=='-1'}">
            <a href="/user/create">Création compte</a>
            <a href="/user/connect">Connexion compte</a>
        </c:when>
        <c:otherwise>
            <a href="/user/profile">Consultation compte</a>
            <a href="/user/disconnect">Déconnexion compte</a>
        </c:otherwise>
    </c:choose>

    <a href="#" onclick="toBasket()"><img src="${prefix}/image/trolley"/></a>
</header>
</html>
<script>
    function toBasket(){
        if (${userId!='-1'}){
            window.location = "${prefix}/basket";
        } else{
            window.location = "${prefix}/user/connect";
        }
    }
</script>