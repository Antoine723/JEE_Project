<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Home</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/rating.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/switch.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/carrousel.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/component/double_range.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/home.js"/>"></script>
</head>
<body>
    <section>
        <div id="side">
            <div id="sort">
                <h3 class="center">Tris</h3>
                <hr>
                <div>
                    ↘
                    <label for="order_by" class="switch">
                        <input class="search_btn" name="order_by" id="order_by" type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                    ↗
                </div>
                <div>
                    <input class="search_btn" name="sort_by" id="sort_name" type="radio" value="name" checked>
                    <label for="sort_name">Alphabétique</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="sort_price" type="radio" value="price">
                    <label for="sort_price">Prix</label>
                    <br>
                    <input class="search_btn" name="sort_by" id="sort_score" type="radio" value="score">
                    <label for="sort_score">Popularité</label>
                </div>
            </div>
            <br>
            <br>
            <div id="filter">
                <h3 class="center">Filtres</h3>
                <hr>
                <div class="center">
                    <div class="subtitle">Fourchette de prix</div>
                    <div>
                        <label id="price_min_val" for="price_min"></label>
                        <input type="hidden" id="price_min_abs" value="${price_min-6}">
                        <input class="search_btn" type="range" id="price_min" name="price_min" min="${price_min-6}" max="${price_max}" value="${price_max}" step="10" dir="rtl">
                        <input class="search_btn" type="range" id="price_max" name="price_max" max="${price_max+6}" value="${price_max}" step="10">
                        <label id="price_max_val" for="price_max"></label>
                    </div>
                </div>
                <hr>
                <div>
                    <div class="center subtitle">Moyenne minimale</div>
                    <div class="rating">
                        <c:forEach var="i" begin="1" end="5">
                            <input class="search_btn" name="stars" id="${6-i}" type="radio" value="${6-i}">
                            <label for="${6-i}">★</label>
                        </c:forEach>
                    </div>
                </div>
                <hr>
                <div>
                    <div class="center subtitle">Consoles</div>
                    <div>
                        <c:forEach items="${console_names}" var="name">
                            <label>
                                <input class="search_btn" type="checkbox" name="console_choice" value="${name}">
                                ${name}
                            </label>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div>
                <h2><label for="search">Votre recherche : </label></h2>
                <input id="search" type="text" name="search">
                <button id="reset">X</button>
            </div>
            <hr>
            <div id="result"></div>
        </div>
    </section>
</body>
</html>
