<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>manageProduct.jsp</title>
</head>
<body>
	
	<jsp:include page="common/headerNav.jsp"/>
	
	<div class="totalList">
	<br>
	<h3>Manage Product</h3>
	<br>
		
		<div class="list">
		<table>
			<tr>
				<th>상품 번호</th>
				<th>카테고리</th>
				<th>제품명</th>
				<th>가격</th>
				<th>색상</th>
				<th>사이즈</th>
			</tr>
			<c:forEach items="${requestScope.productAll}" var="product">
				<form action="controller?command=updateProduct&idx=${product.idx}" method="post">
					<tr>
						<td>${product.idx}</td>
						<td><input type="text" name="category" value="${product.category}" placeholder="카테고리를 입력해주세요." required></td>
						<td><input type="text" name="name" value="${product.name}" placeholder="제품명을 입력해주세요." required></td>
						<td><input type="text" name="price" value="${product.price}" placeholder="가격을 입력해주세요." required></td>
						<td><input type="text" name="color" value="${product.color}" placeholder="색상을 입력해주세요." required></td>
						<td><input type="text" name="psize" value="${product.psize}" placeholder="사이즈를 입력해주세요." required></td>
						
						<td><input type="submit" value="제품 수정"></td>
						<td><a href="controller?command=deleteProduct&idx=${product.idx}">제품 삭제</a></td>
					</tr>
				</form>
			</c:forEach>
		</table>
		<a href="addProduct.jsp">제품 추가</a>
		</div>
	</div>

</body>
</html>