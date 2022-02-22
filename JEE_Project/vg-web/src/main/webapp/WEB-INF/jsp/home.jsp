<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Home</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/rating.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/switch.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/home.js"/>"></script>
</head>
<body>
    <section>
        <div id="filters">
            <h3>filtres latéraux</h3>
            <div>
                <label>
                    Prix<br>
                    <span>
                        <span id="price_min_val"></span>
                        <input type="hidden" id="price_min_abs" value="${price_min-6}">
                        <input class="search_btn" type="range" id="price_min" name="price_min" min="${price_min-6}" max="${price_max}" value="${price_max}" step="10" dir="rtl">
                        <input class="search_btn" type="range" id="price_max" name="price_max" max="${price_max+6}" value="${price_max}" step="10">
                        <span id="price_max_val"></span>
                    </span>
                </label>
            </div>
            <hr>
            <div>
                Note des utilisateurs :
                <div class="rating">
                    <c:forEach var="i" begin="1" end="5">
                        <input name="stars" id="${6-i}" type="radio" value="${6-i}">
                        <label for="${6-i}">★</label>
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
                    <input class="search_btn" name="sort_by" id="sort_name" type="radio" value="name" checked>
                    <label for="sort_name">nom</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="sort_score" type="radio" value="score">
                    <label for="sort_score">note</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="sort_price" type="radio" value="price">
                    <label for="sort_price">prix</label>
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
