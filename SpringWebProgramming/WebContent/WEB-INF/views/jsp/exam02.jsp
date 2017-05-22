<%--
1. 작성 ><%@..%>
2. 종류
	<%@page..%> -jsp가 무엇을 만들어내느냐? 실형결과가 무엇인지
	<%@include ..%> -외부파일을 가져와서 포함시킴
	<%@taglib ..%>
--%>
<%@page import="java.util.*, java.io.*"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
		<h1>다른 패키지 클래스 사용</h1>
		<%@include file="exam02_include.jsp" %>


	</body>
</html>
