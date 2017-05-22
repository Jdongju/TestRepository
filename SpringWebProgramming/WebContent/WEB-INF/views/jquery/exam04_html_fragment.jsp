<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%for (int i = 1; i <= 4; i++) {%>
<div>
	<img src="<%=application.getContextPath()%>/resources/image/member0<%=i%>.png" width="30" height="30"/>
	<span>메시지<%=i%></span>
</div>
<%}%>
