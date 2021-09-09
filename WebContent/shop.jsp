<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">

<title>shop.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />

	<br>
	
	<jsp:include page="common/searchBar.jsp" />
	
	<br>
	<br>

	<div id="shopList">
		<c:forEach items="${requestScope.productAll}" var="product">
			<div class="product">
				<img class="product" alt="img" src="images/${product.idx}.jpg">
				<div class="info">
					<h3>${product.name}</h3>
					<p>${product.price}</p>
					<div class="btn">
						<a href="controller?command=addCart&idx=${product.idx}">장바구니 담기</a>
						<a href="controller?command=addOrders&idx=${product.idx}">주문하기</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>