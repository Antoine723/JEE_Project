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
            <tr><td colspan="2"><br></td></tr>
            <tr>
                <th>Mail</th>
                <td>${user.mail}</td>
            </tr>
            <tr><td colspan="2"><br></td></tr>
            <tr>
                <th>Adresse</th>
                <td>${user.address}</td>
            </tr>
        </table>
        <br>
        <a href="/user/update"><button>Modifier</button></a>
        <a href="/user/delete"><button>Supprimer</button></a>
    </section>
</body>
</html>
