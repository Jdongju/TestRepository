<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			function handleBtnUpdate(){
				var mpassword=$("#mpassword").val();
				if(mpassword==""){
					$("#mpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
					$("#mpassword").css("border-color", "blue");
					return
				}
				$.ajax({
					url:"exam06CheckMpassword",
					method:"post",
					data:{"mid":"${member.mid}", "mpassword":mpassword},
					success:function(data){
						if(data.result=="success"){
							location.href="exam06Update?mid=${member.mid}"
						}else{
							$("#mpassword").val("");
							$("#mpassword").attr("placeholder", "비밀번호가 틀렸습니다.");
							$("#mpassword").css("border-color", "red")
						}
					}
				});
			}
		</script>
	
	</head>
	<body>
		<h4>회원정보 상세보기</h4>
		<hr/>
		<form method="post" style="pading: 0 20" enctype="multipart/form-data"> <%--멀티파트이면 메소드가 포스트여야만한다.--%>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="아이디" name="mid" value="${member.mid}" disabled /> 
					<!--Requeset속성중 객체 member 에서 mid의 getter를 찾아와서 "아이디"자리에 해당 아이디 값을 넣는다.   -->
					<!--JDBC컨트롤러에서 member객체를 저장해 주어야 사용 가능  -->
				</div>
			</div>
			
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="이름" name="mname" value="${member.mname}" disabled /> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-tag"></span>
					</span>
					<input type="date" class="form-control" placeholder="가입날짜" name="mdate" value="${member.mdate}" disabled/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="전화번호" name="mtel" value="${member.mtel}" disabled /> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="이메일" name="memail" value="${member.memail}" disabled /> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="나이" name="mage" value="${member.mage}" disabled /> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" class="form-control" placeholder="주소" name="maddress" value="${member.maddress}" disabled /> 
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-camera"></span>
					</span>
					<a href='#' class="form-controll">${member.moriginalfilename}</a>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" class="form-control" placeholder="비밀번호" name="mpassword"  id="mpassword"/>
					<!--비밀번호는 아이디 지정.  -->
				</div>
			</div>
			
			<a href="exam06" class="btn btn-success">목록</a>
			<input onclick="handleBtnUpdate()" type="button" class="btn btn-warning" value="수정"/>
			<input onclick="handleBtnDelete()" type="button" class="btn btn-danger" value="삭제"/>
			
		</form>
	</body>
</html>