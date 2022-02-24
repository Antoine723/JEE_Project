<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Product</title>
    <jsp:include page="component/header.jsp"/>
    <link href="<c:url value="/css/component/rating.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/product.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/component/check_user.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/product.js"/>"></script>
    <%
        try {
            long userId = (long) session.getAttribute("userID");
            pageContext.setAttribute("userId", userId);
        } catch (NullPointerException | NumberFormatException ignore) {
            pageContext.setAttribute("userId", "-1");
        }
    %>
</head>
<body>
    <section>
        <input type="hidden" id="productId" value="${product.id}">
        <input type="hidden" id="productQuantity" value="${product.quantity}">
        <h1 class="center">Fiche produit</h1>
        <div class="card">
            <img src="/image/${product.img}<c:if test="${not empty consoleGameName}">_${consoleGameName}</c:if>" alt="product_img"/>
            <div>
                <p><span class="bold">Nom :</span> ${product.name}</p>
                <p><span class="bold">Date de sortie :</span> ${dateFormat.format(product.releaseDate)}</p>
                <p>
                    <span class="bold">Note des utilisateurs :</span>
                    <c:choose>
                        <c:when test="${product.rating == null}">
                            N/A
                        </c:when>
                        <c:otherwise>
                            ${product.rating}/5
                        </c:otherwise>
                    </c:choose>
                </p>
                <p><span class="bold">Nombre de commentaires :</span> ${product.comments.size()}</p>
                <p><span class="bold">Prix :</span> ${product.price} €</p>
                <form method="POST" action="/basket/qty/add/${product.id}">
                    <input type="hidden" name="redirect" value="/product/${product.id}/${consoleGameName}">
                    <p>
                        <label class="bold" for="quantity">Quantité :</label>
                        <button type="button" id="decreaseQuantity">▼</button>
                        <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.quantity}"/>
                        <button type="button" id="increaseQuantity">▲</button>
                    </p>
                    <p><input type="submit" class="btn" value="Ajouter au panier"></p>
                </form>
            </div>
        </div>
        <br>
        <hr>
        <h1 class="center">Commentaires</h1>
        <div id="comments">
            <div>
                <h3 class="center"><label for="comment">Votre commentaire</label></h3>
                <c:choose>
                    <c:when test="${userId=='-1'}">
                        Connectez-vous pour commenter !
                    </c:when>
                    <c:otherwise>
                        <form class="center" method="POST" action="/product/${product.id}/comment">
                            <input type="hidden" name="consoleUrl" value="${consoleGameName}">
                            <div class="rating">
                                <c:forEach var="i" begin="1" end="5">
                                    <input name="rating" id="${6-i}" type="radio" value="${6-i}" required>
                                    <label for="${6-i}">★</label>
                                </c:forEach>
                            </div>
                            <textarea id="comment" name="newComment" rows="5" placeholder="Ajouter un commentaire" required></textarea>
                            <br><br>
                            <input class="btn" type="submit" value="Ajouter">
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <h3 class="center">Tous les commentaires</h3>
                <div id="commentList">
                    <c:choose>
                        <c:when test="${product.comments.size() == 0}">
                            <p class="center">Pas de commentaire!</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${product.comments}" var="comment">
                                <article>
                                    <div>
                                        <div><span class="bold">Auteur :</span> ${comment.user.name}</div>
                                        <div class="rated">
                                            <c:forEach var="i" begin="1" end="5">
                                                <span <c:if test="${i <= comment.rating}">class="colored"</c:if>>★</span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <p>${comment.content}</p>
                                </article>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
