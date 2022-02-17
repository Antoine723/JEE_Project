<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Products</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<c:forEach items="${products}" var="product">
    <p>${product.name}</p>
</c:forEach>
</body>
</html>
