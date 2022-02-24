<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Update user</title>
    <jsp:include page="component/header.jsp"/>
</head>
<body>
    <section>
        <h1>Update profile</h1>
        <form method="POST" action="/user/update">
            <div>
                <label for="name">Name: </label>
                <input id="name" type="text" name="name" value="${user.name}" required>
            </div>
            <div>
                <label for="password">Password: </label>
                <input id="password" type="password" name="password">
            </div>
            <div>
                <label for="confirm_password">Password confirmation: </label>
                <input id="confirm_password" type="password" name="confirm_password">
            </div>
            <div>
                <label for="mail">Mail: </label>
                <input id="mail" type="email" name="mail" value="${user.mail}">
            </div>
            <div>
                <label for="address">Address: </label>
                <input id="address" type="text" name="address" value="${user.address}">
            </div>
            <input class="btn" type="submit" value="validate">
        </form>
    </section>
</body>
</html>
