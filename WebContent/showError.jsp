<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>showError.jsp</title>
</head>
<body>
	<jsp:include page="common/headerNav.jsp" />
	<br>
	
	<h3>error</h3>
	<h3>${requestScope.errorMsg}</h3>

</body>
</html>