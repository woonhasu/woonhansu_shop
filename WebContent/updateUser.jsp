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
					<h3>이름</h3>
					<td>${sessionScope.user.name}</td><br>
					<h3>아이디</h3>
					<td>${sessionScope.user.id}</td><br>
					<h3>비밀번호</h3>
					<td><input type="text" name="pw"
						value="${sessionScope.user.pw}" placeholder="비밀번호를 입력해주세요."
						required></td><br>
					<h3>주소</h3>
					<td><input type="text" name="address"
						value="${sessionScope.user.address}" placeholder="주소를 입력해주세요."
						required></td><br>
					<h3>전화번호</h3>
					<td><input type="text" name="phone"
						value="${sessionScope.user.phone}" placeholder="전화번호를 입력해주세요."
						required></td><br><br>
				</tr>
				<tr>
					<td colspan="5"><input type="submit" value="수정">
					<input type="reset" value="수정취소"></td>
				</tr>
			</thead>
		</form>
	</div>
</body>
</html>