<%-- 
    Document   : exan03
    Created on : 2017. 4. 27, 오후 2:30:09
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
			function changeDivContent() {
				$("#div1").html("<p>태그로 변경된 내용</p>");
				$("#div2").append("<p>태그로 변경된 내용</p>");
			}
			function changeCSS() {
				$("#div3").css("background-color", "#ffff00");
				$("#div3").css("width", "300px");
			}
		</script>
	</head>
	<body>
		<h1>DOM 내용변경</h1>

		<h3>DIV 내용 변경</h3>
		<a href ="javascript:changeDivContent()">DIV 내용 변경</a>
		<div id="div1">
			<img src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
		</div>
		<div id="div2">
			<img src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
		</div>

		<h3>CSS 내용 변경</h3>
		<a href ="javascript:changeCSS()">CSS 내용 변경</a>
		<div id="div3" style="background-color:coral; border: 1px solid balck; width: 100px; height: 100px">
		</div>
	</body>
</html>
