<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>User</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/user.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <section class="center">
        <h1>Informations de compte</h1>
        <br>
        <table>
            <tr>
                <th>Nom utilisateur</th>
                <td>${user.name}</td>
            </tr>
            <c:if test="${not empty user.mail}">
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th>Mail</th>
                    <td>${user.mail}</td>
                </tr>
            </c:if>
            <c:if test="${not empty user.address}">
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th>Adresse</th>
                    <td>${user.address}</td>
                </tr>
            </c:if>
        </table>
        <br>
        <a href="/user/delete"><button class="big">Supprimer</button></a>
        <a href="/user/update"><button class="big">Modifier</button></a>
    </section>
</body>
</html>
