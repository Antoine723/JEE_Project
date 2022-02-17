<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <link href="<c:url value="/css/head.css"/>" rel="stylesheet" type="text/css">
</head>
<header class="topnav">
    <%
        long userId = -1;
        try {
            userId = (long) request.getSession().getAttribute("userID");
        } catch (NullPointerException | NumberFormatException ignore) {
        }
        pageContext.setAttribute("userId", userId);
    %>

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
</header>
</html>
