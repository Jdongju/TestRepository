<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<%=application.getContextPath()%> Home
		<hr/>
		<h4>html태그 </h4>
		<a href="html/exam01" class="btn btn-warning">exam01</a>
<br/> 
		<h4>CSS </h4>
		<a href="css/exam01" class="btn btn-warning">exam01</a>
		<a href="css/exam02" class="btn btn-warning">exam02</a>
		<a href="css/exam03" class="btn btn-warning">exam03</a>
		<br/>
		<h4>JavsScript </h4>
		<%for (int i = 1; i < 12; i++) {
				String exam = "exam";
				if (i < 10) {
					exam += "0" + i;
				} else {
					exam += String.valueOf(i);
				}%>
		<a href="javascript/<%=exam%>" class="btn btn-warning"><%=exam%></a>
		<%}%>
		<br/> 
		<h4>Jquery</h4>
		<%for (int i = 1; i < 5; i++) {
				String exam = "exam";
				if (i < 10) {
					exam += "0" + i;
				} else {
					exam += String.valueOf(i);
				}%>
		<a href="jquery/<%=exam%>" class="btn btn-warning"><%=exam%></a>
		<%}%>
		</br>

		<h4>Bootstrap</h4>
		<%for (int i = 1; i < 4; i++) {
				String exam = "exam";
				if (i < 10) {
					exam += "0" + i;
				} else {
					exam += String.valueOf(i);
				}%>
		<a href="jquery/<%=exam%>" class="btn btn-warning"><%=exam%></a>
		<%}%>
		</br>
		<h4>JSP </h4>
		<%for (int i = 1; i < 4; i++) {
				String exam = "exam";
				if (i < 10) {
					exam += "0" + i;
				} else {
					exam += String.valueOf(i);
				}%>
		<a href="jsp/<%=exam%>" class="btn btn-warning"><%=exam%></a>
		<%}%>
</br>
		<h4>HTTP</h4>
		<h5>1. 요청방식</h5>
		1. GET 방식 : <a href="http/exam01" class="btn btn-warning">exam01</a><br/>
		2. POST 방식 : 
		<form method="post" action="http/exam01" style="display: inline">
			<input type="submit" value="exam01" class="btn btn-primary"/>
		</form>

		<h4> 요청 HTTP 정보얻기</h4>
		<%-- bno, type, hobby 등등은 요청 파라미터이다 요청파라미터에서 동일한 값을 찾아서 대입해준다.,--%>
		<a href="http/exam02?type=freeboard&bno=5&hobby=baseball&hobby=soccer" class="btn btn-primary"> exam02</a>
		<a href="http/exam03?type=freeboard&bno=5&hobby=baseball&hobby=soccer" class="btn btn-primary"> exam02</a>

		<h4>폼 제출</h4>
		<a href="form/exam01" class="btn btn-primary"> 회원가입양식 요청</a>
		<h4>파일업로드</h4>
		<a href="form/exam02" class="btn btn-primary"> 회원가입양식 요청</a>
		<h4>파일다운로드</h4>
		<a href="file/exam03" class="btn btn-primary"> 파일로 저장</a>
		<img src="file/exam03" width="100" height="100"/>
		
		<h4>의존성 주입</h4>
		<a href="di/exam01" class="btn btn-primary"> 회원가입</a>
		<a href="di/exam02" class="btn btn-primary"> 로그인</a>
		
		<h4>리다이렉트(재요청)</h4>
		<a href="redirect/list" class="btn btn-primary"> 게시물 목록</a>
		<a href="redirect/write" class="btn btn-primary"> 게시물 쓰기</a>
		
		<h4>JDBC</h4>
		<a href="jdbc/exam01" class="btn btn-primary"> 글쓰기</a>
		<a href="jdbc/exam02" class="btn btn-primary"> 글쓰기</a>
		<a href="jdbc/exam03" class="btn btn-primary"> 회원가입</a>
		<a href="jdbc/exam04" class="btn btn-primary"> 게시물 목록</a>
		<a href="jdbc/exam05" class="btn btn-primary"> 페이지 목록</a>
		<a href="jdbc/exam06" class="btn btn-primary"> 회원 페이지 목록</a>
	</body>
</html>
