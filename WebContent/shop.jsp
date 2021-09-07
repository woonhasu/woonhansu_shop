<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp"/>
	
	<br>
	
	<div id="shopList">
		<!-- 여기서 getAll() 을 통한  -->
		<c:forEach items="${requesScope.productAll}" var="product"> 
			<!-- 여기다 제품들 출력 가능할듯!!! -->
			<img class="product" alt="img" src="images/${product.idx}.jpg">
			<div class="productInfo">
				<h3>${product.name}</h3>
				<p>${product.price}</p>
			</div>
		</c:forEach>
		
<!-- 		<div class="product">
			<img class="product" alt="img" src="images/1.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>$120</p>
			</div>
		</div>
		
		<div class="product">
			<img class="product" alt="img" src="images/2.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>price</p>
			</div>
		</div>
		
		<div class="product">
			<img class="product" alt="img" src="images/3.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>price</p>
			</div>
		</div>
		
		<div class="product">
			<img class="product" alt="img" src="images/1.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>$120</p>
			</div>
		</div>
		
		<div class="product">
			<img class="product" alt="img" src="images/2.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>price</p>
			</div>
		</div>
		
		<div class="product">
			<img class="product" alt="img" src="images/3.jpg">
			<div class="productInfo">
				<h3>Product Name</h3>
				<p>price</p>
			</div>
		</div>
		 -->

		
	</div>
	
</body>
</html>