<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Products</title>
    <jsp:include page="component/header.jsp"/>
</head>
<body>
    <h1>Consoles</h1>
    <div>
        <c:forEach items="${consoles}" var="console" varStatus="loop">
        <article>
            <a href="/product/${console.id}">
                <h3>${console.name}</h3>
                <p>
                    Note :
                    <c:choose>
                        <c:when test="${console.rating == null}">
                            N/A
                        </c:when>
                        <c:otherwise>
                            ${console.rating}/5
                        </c:otherwise>
                    </c:choose>
                </p>
                <img src="/image/${console.img}" style="max-height: 300px; max-width: 300px;" alt="console_img"/>
            </a>
        </article>
        <c:if test="${!loop.last}"><hr></c:if>
        </c:forEach>
    </div>
    <hr>
    <hr>
    <h1>Jeux</h1>
    <div>
        <c:forEach items="${games}" var="game" varStatus="loop">
        <article>
            <h3>${game.name}</h3>
            <p>
                Note :
                <c:choose>
                    <c:when test="${game.rating == null}">
                        N/A
                    </c:when>
                    <c:otherwise>
                        ${game.rating}/5
                    </c:otherwise>
                </c:choose>
            </p>
            Dispo sur :
            <ul>
                <c:forEach items="${game.consoles}" var="console">
                <li>
                    <a href="/product/${game.id}/${console.name}">
                        ${console.name}
                        <br>
                        <img src="/image/${game.img}_${console.name}" style="max-height: 300px; max-width: 300px;" alt="game_img"/>
                    </a>
                </li>
                </c:forEach>
            </ul>
        </article>
        <c:if test="${!loop.last}"><hr></c:if>
        </c:forEach>
    </div>
</body>
</html>
