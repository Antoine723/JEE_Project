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
            < % String username = request.getParameter("username"); %>
            <p>Name : < % out.println(username); %></p>
            <p>Mail : ...</p>
            <p>Address : ...</p>
        </div>
        <a href="/user/update/1">update profile</a><br>
        <a href="/user/delete/1">delete profile</a><br>
    </section>
</body>
</html>
