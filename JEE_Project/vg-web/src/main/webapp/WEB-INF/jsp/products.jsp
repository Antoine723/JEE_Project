<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Products</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<h1>Consoles</h1>
<c:forEach items="${consoles}" var="console">
    <a href="/product/${console.id}">${console.name} <img src="/image/${console.img}" style="max-height: 300px; max-width: 300px;"/></a>
</c:forEach>
<h1>Jeux</h1>
<c:forEach items="${games}" var="game">
    <p>${game.name} ${game.rating}/5 </p>
    Dispo sur :

    <ul>
        <c:forEach items="${game.consoles}" var="console">
            <li><a href="/product/${game.id}/${console.name}">${console.name} <img src="/image/${game.img}_${console.name}" style="max-height: 300px; max-width: 300px;"/></a></li>
        </c:forEach>
    </ul>
    ------
</c:forEach>
</body>
</html>