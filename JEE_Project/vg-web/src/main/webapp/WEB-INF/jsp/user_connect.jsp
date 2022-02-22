<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Connect user</title>
    <jsp:include page="component/header.jsp"/>
</head>
<body>
    <section>
        <h1>Account connection page</h1>
        <div>${error_msg}</div>
        <form method="POST" action="/user/connect">
            <div>
                <label for="name">Name: </label>
                <input id="name" type="text" name="name" required>
            </div>
            <div>
                <label for="password">Password: </label>
                <input id="password" type="password" name="password" required>
            </div>
            <input type="submit" value="validate">
        </form>
    </section>
</body>
</html>
