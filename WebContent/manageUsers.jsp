<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="https://bit.ly/38MHrcB">
<link rel="stylesheet" href="css/style.css">
<title>manageUsers.jsp</title>
</head>
<body>
	
	<jsp:include page="common/headerNav.jsp"/>
	
	<div class="totalList">
	<br>
		<h3>Manage Users</h3>
		<br>

		<div class="list">
		<table>
			<tr>
				<th>고객 아이디</th>
				<th>고객 비밀번호</th>
				<th>고객 유형</th>
				<th>고객 이름</th>
				<th>고객 주소</th>
				<th>고객 전화번호</th>
			</tr>
			<c:forEach items="${requestScope.UsersAll}" var="users">
				<form action="controller?command=updateUser&id=${users.id}" method="post">
					<tr>
						<td>${users.id}</td>
						<td><input type="text" name="pw" value="${users.pw}" placeholder="비밀번호를 입력해주세요." required></td>
						<td>${users.admin}</td>
						<td>${users.name}</td>
						<td><input type="text" name="address" value="${users.address}" placeholder="주소를 입력해주세요." required></td>
						<td><input type="text" name="phone" value="${users.phone}" placeholder="전화번호를 입력해주세요." required></td>
						
						<td><input type="submit" value="고객 정보 수정"></td>
						<td><a href="controller?command=deleteUser&id=${users.id}">고객 삭제</a></td>
					</tr>
				</form>
			</c:forEach>
		</table>
		</div>
	</div>

</body>
</html>