<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Home</title>
    <jsp:include page="head.jsp"/>
    <link href="<c:url value="/css/component/switch.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/home.js"/>"></script>
</head>
<body>
    <section>
        <div id="filters">
            <h3>filtres latéraux</h3>
            <div>
                slider prix<br>
                min : ${price_min}<br>
                max : ${price_max}
            </div>
            <hr>
            <div>
                Note des utilisateurs :
                <div class="rating">
                    <c:forEach var = "i" begin = "1" end = "5">
                        <input name="stars" id="${i}" type="radio" value="${i}">
                        <label for="${i}">★</label>
                    </c:forEach>
                </div>
            </div>
            <hr>
            <div>
                Consoles :
                <br>
                <c:forEach items="${console_names}" var="name">
                    <label>
                        <input class="search_btn" type="checkbox" name="console_choice" value="${name}">
                        ${name}
                    </label>
                    <br>
                </c:forEach>
            </div>
            <hr>
            <div>
                Ordre d'affichage :
                <div>
                    descendant
                    <label for="order_by" class="switch">
                        <input class="search_btn" name="order_by" id="order_by" type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                    ascendant
                </div>
                <div>
                    <input class="search_btn" name="sort_by" id="name" type="radio" value="name" checked>
                    <label for="name">nom</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="score" type="radio" value="score">
                    <label for="score">note</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="price" type="radio" value="price">
                    <label for="price">prix</label>
                </div>
            </div>
        </div>
        <div>
            <div>
                <label for="search">Votre recherche : </label>
                <input id="search" type="text" name="search">
                <button id="reset">Reset</button>
            </div>
            <div id="result"></div>
        </div>
    </section>
</body>
</html>
