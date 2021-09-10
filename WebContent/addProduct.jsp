<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>addProduct.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp"/>

	<div class="login"> 
		<form action="controller?command=addProduct" method="post">
			<h3>카테고리를 입력하세요.</h3>
			<input type="text" name="category" required><br>
		
			<h3>제품명을 입력하세요</h3>
			<input type="text" name="name" required><br>
			
			<h3>가격을 입력하세요</h3>
			<input type="text" name="price" required><br>
			
			<h3>색상을 입력하세요</h3>
			<input type="text" name="color" required><br>
			
			<h3>사이즈를 입력하세요</h3>
			<input type="text" name="psize" required><br>
			
			<input class="register" type="submit" value="추가">
		</form>
	</div>
</body>
</html>