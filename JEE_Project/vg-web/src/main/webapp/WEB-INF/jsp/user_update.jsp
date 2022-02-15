<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update user</title>
</head>
<body>
    <header>
        <a href="/home">homepage</a><br>
        <a href="/user/profile">profile</a><br>
        <a href="/user/disconnect">disconnect</a><br>
        <hr>
    </header>
    <section>
        <h1>Update profile</h1>
        <form method="POST" action="/user/update">
            <div>
                <label for="name">Name: </label>
                <input id="name" type="text" name="name" value="..." required>
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
                <input id="mail" type="email" name="mail" value="">
            </div>
            <div>
                <label for="address">Address: </label>
                <input id="address" type="text" name="address" value="">
            </div>
            <input type="submit" value="validate">
        </form>
    </section>
</body>
</html>
