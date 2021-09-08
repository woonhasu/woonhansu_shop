<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>myPage.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp"/>

	<div class="myPage">
		<form action="controller?command=update" method="post">
			<h3>"${sessionScope.user.name}" 님의 의 정보입니다</h3>
			<h3>아이디 : ${sessionScope.user.id} </h3>
			<h3>주소 : ${sessionScope.user.address} </h3>
			<h3>핸드폰 : ${sessionScope.user.phone} </h3>
		</form>
	</div>

</body>
</html>