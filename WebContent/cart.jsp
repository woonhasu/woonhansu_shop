<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>cart.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />
	<br>
	<br>
	<h3>${sessionScope.user.name} 님의 장바구니입니다</h3>
	
	<div id="cartList">
		
		<table border="1">
			<tr>
				<th>상품코드</th>
				<th>상품명</th>
				<th>가격</th>
				<th>색상</th>
				<th>사이즈</th>
			</tr>
			<c:forEach items="${requestScope.cartAll}" var="cart">
				<tr>
					<td>${cart.product.idx}</td>
					<td>${cart.product.name}</td>
					<td>${cart.product.price}</td>
					<td>${cart.product.color}</td>
					<td>${cart.product.psize}</td>
					<td><a href="controller?command=addOrder">주문하기</a></td>
					<td><a href="controller?command=deleteCart&idx=${cart.product.idx}">삭제하기</a></td>
				</tr>
			</c:forEach>
		</table>


</body>
</html>