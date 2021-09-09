<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">

<title>myPage.jsp</title>
</head>
<body>


	<jsp:include page="common/headerNav.jsp" />
	<div class="myPage">
		<form>
			<h3>My Page</h3>


			<h2>${requestScope.successMsg}</h2>
			<br>

			<h3>"${sessionScope.user.name}" 님의 정보입니다</h3>
			<br>
			<h3>아이디 : ${sessionScope.user.id}</h3>
			<br>
			<h3>비밀번호 : ${sessionScope.user.pw}</h3>
			<br>
			<h3>주소 : ${sessionScope.user.address}</h3>
			<br>
			<h3>전화번호 : ${sessionScope.user.phone}</h3>
			<br> 
			<div class="myPagebutton"><a href="userUpdate.jsp">수정하기</a>
			<a href="controller?command=userDelete">탈퇴하기</a></div>
		</form>
	</div>
</body>
</html>