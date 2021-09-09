<%@page import="java.util.HashMap"%>
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
	
	<div class="search">
		<form action="controller?command=search" method="post">
			<input type="text" name="name" required>
			<input type="submit" value="검색">
		</form>
	</div>
	
	<br>
	
	<div class="category">
		<a href="controller?command=getProductAll">전체 상품</a>
		<c:forEach items="${requestScope.productAll}" var="product">
			<a href="controller?command=category&category=${product.category}">${product.category}</a>
		</c:forEach>
	</div>
	
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
						<a href="controller?command=addCart&idx=${product.idx}">장바구니 담기</a>
						<a href="controller?command=addOrders&idx=${product.idx}">주문하기</a>
					</div>
				</div>
			</div>

		</c:forEach>

	</div>

</body>
</html>