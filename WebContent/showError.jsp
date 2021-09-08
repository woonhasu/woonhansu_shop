
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<% String url = application.getContextPath() + "/"; %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css">
<title>showError.jsp</title>
</head>

<body>

	<jsp:include page="common/headerNav.jsp"/>
	
	<br><br><br>

	<center>
		<h3>${requestScope.errorMsg}</h3>
			
	</center>
	
</body>
</html>