<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <link href="<c:url value="/css/head.css"/>" rel="stylesheet" type="text/css">
</head>
<header class="topnav">
    <p><%
        long userId;
        try {
            userId = (long) request.getSession().getAttribute("userID");
            out.println(userId);
        } catch (NullPointerException | NumberFormatException ignore) {
            out.println("pas d'id");
        }
    %></p>

    <a href="/">Accueil</a>
    <a href="/products">Produits</a>
    <a href="/user/create">Création compte</a>
    <a href="/user/connect">Connexion compte</a>
    <a href="/user/profile">Consultation compte</a>
    <a href="/user/disconnect">Déconnexion compte</a>
</header>
</html>
