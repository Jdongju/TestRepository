<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<script>
			//전역변수
			var v1 = "A";

			function fun1() {
				//로컬변수
				var v2 = "B";
				if (true) {
					var v3 = "C";
					v4="D";
				}

				console.log(v1);
				console.log(v2);
				console.log(v3);
				console.log(v4);
			}
			console.log(v1);
			fun1();
			console.log(v2);//(x)
			console.log(v3);//(x)
			console.log(v4);//선언하지 않은 변수 v4는 전역변수이다.
		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
