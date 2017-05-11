<%-- 
    Document   : exam11
    Created on : 2017. 4. 27, 오전 11:54:50
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>

		<script>
			function handleBtn1() {
				var imgArray = $("img");// $는 jQuery()의미
				imgArray.attr("src", "/WebApplication/resources/image/photo02.jpg");
			}
			function handleBtn2() {
				var img1 = $("#img1"); //id로 찾아서 변경할떄는 querySelector
				img1.attr("src", "/WebApplication/resources/image/photo03.jpg");
			}
			function handleBtn3() {
				var class1 = $(".class1"); //class로 찾아서 변경할 떄에는 querySelectorAll
				class1.attr("src", "/WebApplication/resources/image/photo04.jpg");
			}
		</script>
	</head>
	<body>
		<h1>DOM 내용변경</h1>
		<div>
			<button onclick="handleBtn1()">이미지변경</button><br/>
			<img src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
			<img src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
		</div>

		<div>
			<button onclick="handleBtn2()" class="btn btn-success">img1의 이미지변경</button>
			<button onclick="handleBtn3()" class="btn btn-success">class1의 이미지변경</button><br/>
			<img  id="img1" src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
			<img class="class1" src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
			<img class="class1" src="/WebApplication/resources/image/photo01.jpg" width="100px" height="100px"/>
		</div>
	</body>
</html>
