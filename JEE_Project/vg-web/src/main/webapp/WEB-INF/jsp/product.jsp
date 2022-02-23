<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Product</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/product.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/component/rating.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/component/check_user.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/product.js"/>"></script>
    <%
        try {
            long userId = (long) request.getSession().getAttribute("userID");
            pageContext.setAttribute("userId", userId);
        } catch (NullPointerException | NumberFormatException ignore) {
            pageContext.setAttribute("userId", "-1");
        }
    %>
</head>
<body>
    <section>
        <input type="hidden" id="urlPrefix" value="${prefix}">
        <input type="hidden" id="productId" value="${product.id}">
        <input type="hidden" id="productQuantity" value="${product.quantity}">
        <h1 class="center">Fiche produit</h1>
        <div class="card">
            <img src="/image/${product.img}<c:if test="${not empty consoleGameName}">_${consoleGameName}</c:if>" alt="product_img"/>
            <div>
                <p><span class="subtitle">Nom :</span> ${product.name}</p>
                <p><span class="subtitle">Date de sortie :</span> ${dateFormat.format(product.releaseDate)}</p>
                <p>
                    <span class="subtitle">Note des utilisateurs :</span>
                    <c:choose>
                        <c:when test="${product.rating == null}">
                            N/A
                        </c:when>
                        <c:otherwise>
                            ${product.rating}/5
                        </c:otherwise>
                    </c:choose>
                </p>
                <p><span class="subtitle">Nombre de commentaires :</span> ${product.comments.size()}</p>
                <p><span class="subtitle">Prix :</span> ${product.price} €</p>
                <p>
                    <label class="subtitle" for="quantity">Quantité :</label>
                    <button id="decreaseQuantity">-</button>
                    <input type="number" id="quantity" value="1" min="1" max="${product.quantity}"/>
                    <button id="increaseQuantity">+</button>
                </p>
                <p><button id="addToBasket">Ajouter au panier</button></p>
            </div>
        </div>
        <br>
        <hr>
        <h1 class="center">Commentaires</h1>
        <div id="comments">
            <div>
                <c:choose>
                    <c:when test="${userId=='-1'}">
                        loggez vous pour commenter !
                    </c:when>
                    <c:otherwise>
                        <form action="#" method="POST">
                            <label for="newComment">Votre note :</label>
                            <div class="rating">
                                <c:forEach var="i" begin="1" end="5">
                                    <input name="stars" id="${6-i}" type="radio" value="${6-i}">
                                    <label for="${6-i}">★</label>
                                </c:forEach>
                            </div>
                            <input type="text" id="newComment" placeholder="Ajouter un commentaire"/>
                        </form>
                        <button id="submitComment">Ajouter le commentaire</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <h3>Tous les commentaires</h3>
                <c:forEach items="${product.comments}" var="comment">
                    <p>Note : ${comment.rating}/5</p>
                    <p>Commentaire : ${comment.content}</p>
                </c:forEach>
            </div>
        </div>
    </section>
</body>
</html>
