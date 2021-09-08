<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>userUpdate.jsp</title>
</head>
<body>
	
	<jsp:include page="common/headerNav.jsp"/>
	
	<h2>My Page</h2>
	<br>
	
	<form action="controller?command=userUpdate" method="post">
<table border="1">
	<thead>
		<tr>
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>주소</th><th>전화번호</th>
		</tr>
 	<tr>
 		<td>${sessionScope.user.id}</td>
 		<td><input type="text" name="pw" value="${sessionScope.user.pw}" placeholder="비밀번호를 입력해주세요." required></td>
 		<td>${sessionScope.user.name}</td>
 		<td><input type="text" name="address" value="${sessionScope.user.address}" placeholder="주소를 입력해주세요." required></td>
 		<td><input type="text" name="phone" value="${sessionScope.user.phone}" placeholder="전화번호를 입력해주세요." required></td>
 	</tr>
 	
 	<tr>
 		<td colspan="5">
 			<input type="submit" value="수정">
 			&nbsp;&nbsp;&nbsp;
 			<input type="reset" value="취소">
 		</td>
 	</tr>
 	
</table>
</form>

</body>
</html>