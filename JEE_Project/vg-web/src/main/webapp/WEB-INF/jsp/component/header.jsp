<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <link href="<c:url value="/css/component/header.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src='<c:url value="${pageContext.request.contextPath}/webjars/jquery/3.4.1/jquery.js"/>'></script>
    <script type="text/javascript" src="<c:url value="/js/component/check_user.js"/>"></script>
    <%
        try {
            long userId = (long) session.getAttribute("userID");
            pageContext.setAttribute("userId", userId);
        } catch (NullPointerException | NumberFormatException ignore) {
            pageContext.setAttribute("userId", "-1");
        }
    %>
</head>
<header class="bold">
    <input type="hidden" id="gotUser" value="${userId!='-1'}">

    <div>LOGO</div>

    <a href="/">Accueil</a>
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

    <a href="/basket"><img src="${prefix}/image/trolley" alt="panier_img"/></a>
</header>
</html>
