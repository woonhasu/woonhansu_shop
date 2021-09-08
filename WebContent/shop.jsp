<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>shop.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />

	<br>

	<div id="shopList">
		<!-- 여기서 getAll() 을 통한  -->
		<!-- 여기다 제품들 출력 가능할듯!!! -->
		<c:forEach items="${requestScope.productAll}" var="product">
			<div class="product">
				<img class="product" alt="img" src="images/${product.idx}.jpg">
				<div class="info">
					<h3>${product.name}</h3>
					<p>${product.price}</p>
					<div class="btn">
						<a href="#">장바구니 담기</a>
						<a href="#">주문하기</a>
					</div>
				</div>
			</div>

		</c:forEach>

	</div>

</body>
</html>