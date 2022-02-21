<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Product</title>
    <jsp:include page="head.jsp"/>
    <link href="<c:url value="/css/product.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="card">
        <img src="/image/${product.img}<c:if test="${not empty consoleGameName}">_${consoleGameName}</c:if>" style="max-height: 400px; max-width: 400px;" alt="product_img"/>
        <p>Nom : ${product.name}</p>
        <p>
            Note :
            <c:choose>
                <c:when test="${product.rating == null}">
                    N/A
                </c:when>
                <c:otherwise>
                    ${product.rating}/5
                </c:otherwise>
            </c:choose>
        </p>
        <p>Date de sortie : ${product.releaseDate}</p>
        <p>Prix : ${product.price}</p>
        <p>
            <label for="quantity">Quantité :</label>
            <button id="decreaseQuantity" onclick="decreaseQuantity()">-</button>
            <input type="number" id="quantity" value="1" min="1" max="${product.quantity}"/>
            <button id="increaseQuantity" onclick="increaseQuantity()">+</button>
        </p>
    </div>
    <hr>
    <div id="comments">
        <h1>Commentaires</h1>
        <form action="#" method="POST">
            <label for="newComment">Votre note :</label>
            <div class="rating">
                <c:forEach var = "i" begin = "1" end = "5">
                <input name="stars" id="${6-i}" type="radio" value="${6-i}">
                <label for="${6-i}">★</label>
                </c:forEach>
            </div>
            <input type="text" id="newComment" placeholder="Ajouter un commentaire"/>
        </form>
        <button onclick="submitComment()">Ajouter le commentaire</button>
        <br>
        <h3>Tous les commentaires</h3>
        <c:forEach items="${product.comments}" var="comment">
            <p>Note : ${comment.rating}/5</p>
            <p>Commentaire : ${comment.content}</p>
        </c:forEach>
    </div>
</body>
</html>
<script>
    function submitComment(){
        const stars_input = $("input[name='stars']:checked");
        const rating = stars_input.length === 0 ? "0" : stars_input.val();
        const comment = $("#newComment").val();
        $.ajax({
            url:"/addComment",
            type:"POST",
            data:JSON.stringify({
                comment:comment,
                rating:parseInt(rating),
                productId:parseInt(${product.id})
            }),
            contentType : 'application/json; charset=utf-8',

            success: function(){
                location.reload(); //TODO with param to display success message
            },
            error: function(resp){
                //TODO display error message
            }


        })
    }

    $("#quantity").on("change",function(){
        if ($("#quantity").val() < 1){
            $("#quantity").val(1);
        } else if ($("#quantity").val() > ${product.quantity}){
            $("#quantity").val(${product.quantity});
        }
    })
    
    function decreaseQuantity(){
        if ($("#quantity").val() > 1){
            $("#quantity").val(parseInt($("#quantity").val()) - 1);
        }
    }

    function increaseQuantity(){
        if ($("#quantity").val() < ${product.quantity}){
            $("#quantity").val(parseInt($("#quantity").val()) + 1);
        }
    }
</script>
