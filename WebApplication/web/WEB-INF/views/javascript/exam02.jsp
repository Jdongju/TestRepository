<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<script>
			function total(from, to) {
				if(to==undefined){
					to=from;
					from=1;
				}
				var sum = 0;
				for (var i = from; i <= to; i++) {
					sum += i;
				}
				return sum;
			}
			var result = total(1, 100);
			console.log("result: " + result);
			
			var result2 = total(100);
		</script>
	</head>
	<body>
		<h1>Hello World!</h1>
	</body>
</html>
