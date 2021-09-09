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

	<div class="search">
		<form action="controller?command=search" method="post">
			<input type="text" name="name" required>
			<input type="submit" value="검색">
		</form>
		<div class="category">
			<a href="controller?command=getProductAll">All</a> <a
				href="controller?command=category&category=outer">Outer</a> <a
				href="controller?command=category&category=top">Top</a> <a
				href="controller?command=category&category=bottom">Bottom</a> <a
				href="controller?command=category&category=setup">Setup</a> <a
				href="controller?command=category&category=bag">Bag</a>
		</div>
	</div>
	
	<br>
	
	<%-- <div class="category">
		<a href="controller?command=getProductAll">전체 상품</a>
		<c:forEach items="${requestScope.productAll}" var="product">
			<a href="controller?command=category&category=${product.category}">${product.category}</a>
		</c:forEach>
	</div> --%>
	
	<br>
	
		<c:if test="${empty requestScope.productName}">
			<center>
				<h3>${requestScope.errorMsg}</h3>
			</center>
		</c:if>

	<div id="shopList">
		<c:if test="${not empty requestScope.productCategory}">
			<c:forEach items="${requestScope.productCategory}" var="product">
				<div class="product">
					<img class="product" alt="img" src="images/${product.idx}.jpg">
					<div class="info">
						<h3>${product.name}</h3>
						<p>${product.price}</p>
						<div class="btn">
							<a href="controller?command=addCart&idx=${product.idx}">장바구니
								담기</a> <a href="#">주문하기</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>

</body>
</html>