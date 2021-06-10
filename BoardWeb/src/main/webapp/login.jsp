<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <title>로그인</title> -->
<title><spring:message code="message.user.login.title"/></title>
</head>
<body>
<center>
<!-- <h1>로그인</h1> -->
<h1><spring:message code="message.user.login.title"/></h1>
<a href="login.do?lang=en"> 
	<spring:message code="message.user.login.language.en"/></a>&nbsp;&nbsp;
<a href="login.do?lang=kr">
	<spring:message code="message.user.login.language.ko"/></a>
<hr>
<!-- <form action="login_proc.jsp" method="post"> -->
<form action="login.do" method="post">
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
<!-- 		<td bgcolor="orange">아이디</td> -->
		<td bgcolor="orange"><spring:message code="message.user.login.id"/></td>
		<!-- ${userVO.id } => ModelAttribute 사용하면 객체가 아닌 설정한 ModelAttribute로 mapping한다. -->
		<td><input type="text" name="id" value="${user.id }"/></td>
	</tr>
	<tr>
<!-- 		<td bgcolor="orange">비밀번호</td> -->
		<td bgcolor="orange"><spring:message code="message.user.login.password"/></td>
		<!-- ${userVO.password } => ModelAttribute 사용하면 객체가 아닌 설정한 ModelAttribute로 mapping한다. -->
		<td><input type="password" name="password" value="${user.password }"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
<!-- 			<input type="submit" value="로그인"/> -->
			<input type="submit" value="<spring:message code="message.user.login.loginBtn"/>"/>
		</td>
	</tr>
</table>
</form>
<hr>
</center>
</body>
</html>