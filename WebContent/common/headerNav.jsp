<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<nav class="mainNav">
	<div class="headerNav">
		<div class="logo">
			<h3>
				<a href="index.html">woonhasu</a>
			</h3>
		</div>
		<ul class="navLinks">
			<li><a href="controller?command=getProductAll">Shop</a></li>
				
				<c:if test="${empty sessionScope.user}">
					<li><a href="login.jsp">Sign In</a></li>
				</c:if>
				
				<c:if test="${not empty sessionScope.user}">
						<li><a href="controller?command=logout">Sign Out</a></li>
	        			<li><a href="myPage.jsp?">My Page</a></li>
						<li><a href="controller?command=getUserOrdersAll">Orders</a></li>
						<li><a href="controller?command=getUserCartAll">Cart</a></li>
					<%-- <c:if test="${sessionScope.user.admin}==0"> --%>
					<%-- </c:if> --%>
					
					<!-- 이중 if문은 감지하지 못하는걸까...? -->
					<c:if test="${sessionScope.user.admin}==0">
						<!-- <li><a href="controller?command=logout">Sign Out</a></li>
	        			<li><a href="myPage.jsp?">My Page</a></li> -->
	        			<li><a href="ManageProduct.jsp">Manage Product</a></li>
	        			<li><a href="ManageOrders.jsp">Manage Orders</a></li>
	        			<li><a href="ManageUsers.jsp">Manage Users</a></li>
					</c:if>
				</c:if>
				
		</ul>
	</div>
</nav>