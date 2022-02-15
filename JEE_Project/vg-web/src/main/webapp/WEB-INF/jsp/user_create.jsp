<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create user</title>
</head>
<body>
    <header>
        <a href="/home">homepage</a><br>
        <a href="/user/connect">log to account</a><br>
        <hr>
    </header>
    <section>
        <h1>Account creation page</h1>
        <form method="POST" action="/user/create">
            <div>
                <label for="name">Name: </label>
                <input id="name" type="text" name="name" required>
            </div>
            <div>
                <label for="password">Password: </label>
                <input id="password" type="password" name="password" required>
            </div>
            <div>
                <label for="confirm_password">Password confirmation: </label>
                <input id="confirm_password" type="password" name="confirm_password" required>
            </div>
            <div>
                <label for="mail">Mail: </label>
                <input id="mail" type="email" name="mail">
            </div>
            <div>
                <label for="address">Address: </label>
                <input id="address" type="text" name="address">
            </div>
            <input type="submit" value="validate">
        </form>
    </section>
</body>
</html>
