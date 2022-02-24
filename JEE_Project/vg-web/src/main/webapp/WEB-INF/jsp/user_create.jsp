<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Create user</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/user.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <section class="center">
        <h1>Création de compte</h1>
        <br>
        <div>${error_msg}</div>
        <form method="POST" action="/user/create">
            <table>
                <tr>
                    <th><label for="name">Nom utilisateur</label></th>
                    <td><input id="name" type="text" name="name" required></td>
                </tr>
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th><label for="password">Mot de passe</label></th>
                    <td><input id="password" type="password" name="password" required></td>
                </tr>
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th><label for="confirm_password">Confirmation</label></th>
                    <td><input id="confirm_password" type="password" name="confirm_password" required></td>
                </tr>
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th><label for="mail">Mail</label></th>
                    <td><input id="mail" type="email" name="mail"></td>
                </tr>
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <th><label for="address">Adresse</label></th>
                    <td><input id="address" type="text" name="address" placeholder="N°, Rue, Ville"></td>
                </tr>
            </table>
            <br>
            <input class="btn big" type="submit" value="Valider">
        </form>
    </section>
</body>
</html>
