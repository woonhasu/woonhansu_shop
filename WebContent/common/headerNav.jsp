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
			<li><a href="shop.jsp">Shop</a></li>
			<c:if test="${not empty sessionScope.user}">
				<li><a href="controller?command=logout">Sign Out</a></li>
			</c:if>
			<c:if test="${empty sessionScope.user}">
				<li><a href="login.jsp">Sign In</a></li>
			</c:if>
			<li><a href="#">Cart</a></li>
		</ul>
	</div>
</nav>