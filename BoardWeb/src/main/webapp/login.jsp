<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <title>�α���</title> -->
<title><spring:message code="message.user.login.title"/></title>
</head>
<body>
<center>
<!-- <h1>�α���</h1> -->
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
<!-- 		<td bgcolor="orange">���̵�</td> -->
		<td bgcolor="orange"><spring:message code="message.user.login.id"/></td>
		<!-- ${userVO.id } => ModelAttribute ����ϸ� ��ü�� �ƴ� ������ ModelAttribute�� mapping�Ѵ�. -->
		<td><input type="text" name="id" value="${user.id }"/></td>
	</tr>
	<tr>
<!-- 		<td bgcolor="orange">��й�ȣ</td> -->
		<td bgcolor="orange"><spring:message code="message.user.login.password"/></td>
		<!-- ${userVO.password } => ModelAttribute ����ϸ� ��ü�� �ƴ� ������ ModelAttribute�� mapping�Ѵ�. -->
		<td><input type="password" name="password" value="${user.password }"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
<!-- 			<input type="submit" value="�α���"/> -->
			<input type="submit" value="<spring:message code="message.user.login.loginBtn"/>"/>
		</td>
	</tr>
</table>
</form>
<hr>
</center>
</body>
</html>