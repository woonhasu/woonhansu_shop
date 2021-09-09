<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>orders.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />
	<br>
	<br>
	<h3>${sessionScope.user.name} 님의 주문내역 입니다.</h3>
	
	<c:if test="${empty requestScope.OrdersAll }">
		주문내역이 존재하지 않습니다.
	</c:if>

	<c:if test="${not empty requestScope.OrdersAll }">
		<div id="ordersList">

			<table border="1">
				<tr>
					<th>구매 번호</th>
					<th>구매자 아이디</th>
					<th>상품 번호</th>
					<th>구매 날짜</th>
				</tr>
				<c:forEach items="${requestScope.OrdersAll}" var="orders">
					<tr>
						<td>${orders.idx}</td>
						<td>${orders.users.id}</td>
						<td>${orders.product.idx}</td>
						<td>${orders.date}</td>
						<td><a href="controller?command=deleteOrders&idx=${orders.idx}">주문 취소</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>

</body>
</html>