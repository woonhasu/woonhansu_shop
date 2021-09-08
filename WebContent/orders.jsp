<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	    


<% System.out.println("===================="); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css">
<title>orders.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />
	<br>
	<br>
	<h3>${sessionScope.user.name} 님의 주문내역 입니다.</h3>
	
	<div id="ordersList">
		
		<table border="1">
			<tr>
				<th> 구매 번호 </th>
			 	<th> 구매자 아이디 </th>
				<th> 상품 번호 </th>
				<th> 구매 날짜 </th>
			</tr>
			
			<c:forEach items="${requestScope.OrdersAll}" var="orders">
				<tr>
					<td> ${orders.idx} </td>
				 	<td> ${orders.users.id} </td>
					<td> ${orders.product.idx} </td>
					<td> ${orders.date} </td>
					<td><a href="controller?command=getAllOrders">주문내역</a></td>
					<td><a href="controller?command=deleteOrders">주문 취소</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>