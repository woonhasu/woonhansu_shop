<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>manageOrders.jsp</title>
</head>
<body>
	
	<jsp:include page="common/headerNav.jsp"/>
	
	<div class="totalList">
	<br>
	<h3>Manage Orders</h3>
	<br>
		<div class="list">
			<table>
				<tr>
					<th>구매 번호</th>
					<th>구매자 아이디</th>
					<th>상품 번호</th>
					<th>구매 날짜</th>
				</tr>
				<c:forEach items="${requestScope.OrdersAll}" var="orders">
					<form action="controller?command=updateOrders&idx=${orders.idx}" method="post">
						<tr>
							<td>${orders.idx}</td>
							<td>${orders.users.id}</td>
							<td><input type="text" name="productIdx" value="${orders.product.idx}" placeholder="주문 상품 번호를 입력해주세요." required></td>
							<td>${orders.date}</td>
							
							<td><input type="submit" value="주문 수정"></td>
							<td><a href="controller?command=deleteOrders&idx=${orders.idx}">주문 삭제</a></td>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>