<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="fr">
    <jsp:include page="head.jsp"/>
    <link href="<c:url value="/css/product.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="card">
        <c:choose>
            <c:when test="${not empty consoleGameName}">
                <img src="/image/${product.img}_${consoleGameName}" style="max-height: 400px; max-width: 400px;"/>
            </c:when>
            <c:otherwise>
                <img src="/image/${product.img}" style="max-height: 400px; max-width: 400px;"/>
            </c:otherwise>
        </c:choose>
        <p>Nom : ${product.name}</p>
        <p>Note : <c:choose>
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
        <p>Quantité :
            <button id="decreaseQuantity" onclick="decreaseQuantity()">-</button>
            <input type="number" id="quantity" value="1" min="1" max="${product.quantity}"/>
            <button id="increaseQuantity" onclick="increaseQuantity()">+</button>
        </p>
    </div>
    <div id="comments">
        <h1>Commentaires</h1>
        <form action="#" method="POST">
            <div class="rating">
                <input name="stars" id="5" type="radio" value="5"></a><label for="5">★</label>
                <input name="stars" id="4" type="radio" value="4"></a><label for="4">★</label>
                <input name="stars" id="3" type="radio" value="3"></a><label for="3">★</label>
                <input name="stars" id="2" type="radio" value="2"></a><label for="2">★</label>
                <input name="stars" id="1" type="radio" value="1"></a><label for="1">★</label>
            </div>
            <input type="text" id="newComment" placeholder="Ajouter un commentaire"></input>
        </form>
        <button onclick="submitComment()">Ajouter le commentaire</button>
        <c:forEach items="${product.comments}" var="comment">
            <p>Note : ${comment.rating}</p>
            <p>Commentaire : ${comment.content}</p>
        </c:forEach>
    </div>
</body>
</html>
<script>
    function submitComment(){
        var rating = $("input[name='stars']:checked").length == 0 ? "0" : $("input[name='stars']:checked").val();
        var comment = $("#newComment").val();
        $.ajax({
            url:"/addComment",
            type:"POST",
            data:JSON.stringify({
                comment:comment,
                rating:parseInt(rating),
                productId:parseInt(${product.id})
            }),
            contentType : 'application/json; charset=utf-8',

            success: function(resp){
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
