<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Connect user</title>
</head>
<body>
    <header>
        <a href="/home">homepage</a><br>
        <a href="/user/create">create account</a><br>
        <hr>
    </header>
    <section>
        <h1>Account connection page</h1>
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
