<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>login.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp"/>

	<div class="login"> 
		<form action="controller?command=register" method="post">
			<h3>계정 유형을 선택하세요</h3>
			<div>
				<input class="inputBox" type="radio" name="admin" value="1">관리자
			</div>
			<div>
				<input class="inputBox" type="radio" name="admin" value="0">일반 고객
			</div>
		
			<h3>아이디를 입력하세요</h3>
			<input class="inputBox" type="text" name="id" required><br>
			
			<h3>비밀번호를 입력하세요</h3>
			<input class="inputBox" type="password" name="pw" required><br>
			
			<h3>이름</h3>
			<input class="inputBox" type="text" name="name" required><br>
			
			<h3>주소</h3>
			<input class="inputBox" type="text" name="address" required><br>
			
			<h3>연락처</h3>
			<input class="inputBox" type="text" name="phone" required><br>
			
			<input class="register" type="submit" value="회원가입">
		</form>
	</div>
	
	<br><br><br>

</body>
</html>