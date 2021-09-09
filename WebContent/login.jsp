<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>login.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp"/>

	<div class="login">
		<form action="controller?command=login" method="post">
			<h3>아이디를 입력하세요</h3>
			<input class="inputBox" type="text" name="id" required><br>
			<h3>비밀번호를 입력하세요</h3>
			<input class="inputBox" type="password" name="pw" required><br>
	
			<input class="submit" type="submit" value="로그인">
			<input class="register" onclick="location.href='register.jsp'" value="회원가입">
		</form>
	</div>

</body>
</html>