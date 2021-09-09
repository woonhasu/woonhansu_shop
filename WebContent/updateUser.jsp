<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>userUpdate.jsp</title>
</head>
<body>

	<jsp:include page="common/headerNav.jsp" />
	<br>

	<div class=userUpdate>
		<form action="controller?command=updateUser" method="post">
			<h3>Edit Profile</h3>
			<br>
			<thead>
				<tr>
					<h2>이름</h2>
					<td>${sessionScope.user.name}</td><br><br>
					<h2>아이디</h2>
					<td>${sessionScope.user.id}</td><br><br>
					<h2>비밀번호</h2>
					<td><input type="text" name="pw"
						value="${sessionScope.user.pw}" placeholder="비밀번호를 입력해주세요."
						required></td><br>
					<h2>주소</h2>
					<td><input type="text" name="address"
						value="${sessionScope.user.address}" placeholder="주소를 입력해주세요."
						required></td><br>
					<h2>전화번호</h2>
					<td><input type="text" name="phone"
						value="${sessionScope.user.phone}" placeholder="전화번호를 입력해주세요."
						required></td><br><br>
				</tr>
				<tr>
					<td colspan="5"><input type="submit" value="수정">
						&nbsp;&nbsp;&nbsp; <input type="reset" value="수정취소"></td>
				</tr>
			</thead>
		</form>
	</div>
</body>
</html>