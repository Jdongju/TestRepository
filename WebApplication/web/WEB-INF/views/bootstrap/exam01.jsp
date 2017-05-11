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
	</head>
	<body>
		<h3> 버튼</h3>
		<button>Default</button>
		<button type="button" class="btn btn-default">Default</button>
		<button type="button" class="btn btn-primary">Primary</button>
		<button type="button" class="btn btn-success">Success</button>
		<button type="button" class="btn btn-info">Info</button>
		<a class="btn btn-info" href="/WebApplication">a태그</a>
		<input type="button" value="input 태그"  class="btn btn-info"/>
		<h3> 테이블</h3>
		<table class="table table-bordered table-hover">
			<tr class="active">
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
			</tr>
			<tr>
				<td>1</td>
				<td class="success">오늘은 축구 하는 날</td>
				<td>성근</td>
			</tr>
			<tr>
				<td>2</td>
				<td>자바 학습하기 좋은 날</td>
				<td>현규</td>
			</tr>

		</table>
		<h3> 이미지</h3>
		<img src="/WebApplication/resources/image/photo1.jpg" width="100" height="100"/>
		<img class="img-rounded" src="/WebApplication/resources/image/photo1.jpg" width="100" height="100"/>
		<img class="img-circle" src="/WebApplication/resources/image/photo1.jpg" width="100" height="100"/>
		<img class="img-thumbnail" src="/WebApplication/resources/image/photo1.jpg" width="100" height="100"/>

		<form>
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label>
				<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			</div>
		</form>
	</body>
</html>
