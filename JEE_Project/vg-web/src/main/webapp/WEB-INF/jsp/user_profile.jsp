<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>User</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
    <section>
        <h1>User profile</h1>
        <div>
            <p>Name : ${user.name}</p>
            <p>Mail : ${user.mail}</p>
            <p>Address : ${user.address}</p>
        </div>
        <a href="/user/update">update profile</a><br>
        <a href="/user/delete">delete profile</a><br>
    </section>
</body>
</html>
