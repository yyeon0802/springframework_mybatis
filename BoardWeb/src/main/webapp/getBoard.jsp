<%-- <%@ page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page import="com.springbook.biz.board.BoardVO"%> --%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%
	/* DispatcherServlet�� ó�� 
	
	// 1. �˻��� �Խñ� ��ȣ ����
	String seq = request.getParameter("seq");
	
	// 2. DB ���� ó��
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	
	BoardDAO boardDAO = new BoardDAO();
	BoardVO board = boardDAO.getBoard(vo); */

	// ���ǿ� ����� �Խñ� ������ ������.
	/*BoardVO board = (BoardVO)session.getAttribute("board");*/
%>

<!-- 3. ���� ȭ�� ���� -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ��</title>
</head>
<body>
<center>
<h1>�� ��</h1>
<!-- <a href="logout_proc.jsp">Log-out</a> -->
<a href="logout.do">Log-out</a>
<hr>
<!-- <form action="updateBoard_proc.jsp" method="post"> -->
<form action="updateBoard.do" method="post">
<!-- �ۼ��� ��� �߰� -->
<%-- <input name="seq" type="hidden" value="<%= board.getSeq() %>"/> --%>
<input name="seq" type="hidden" value="${board.seq}"/>
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="orange" width="70">����</td>
<%-- 		<td align="left"><input name="title" type="text" value="<%= board.getTitle() %>"/></td> --%>
		<td align="left"><input name="title" type="text" value="${board.title}"/></td>
	</tr>
	<tr>
		<td bgcolor="orange">�ۼ���</td>
		<%-- <td align="left"><%= board.getWriter() %></td> --%>
		<td align="left">${board.writer}</td>
	</tr>
	<tr>
		<td bgcolor="orange">����</td>
<%-- 		<td align="left"><textarea name="content" cols="40" rows="10"><%= board.getContent() %></textarea></td> --%>
		<td align="left"><textarea name="content" cols="40" rows="10">${board.content}</textarea></td>
	</tr>	
	<tr>
		<td bgcolor="orange">�����</td>
<%-- 		<td align="left"><%= board.getRegDate() %></td> --%>
		<td align="left">${board.regDate}</td>
	</tr>
	<tr>
		<td bgcolor="orange">��ȸ��</td>
<%-- 		<td align="left"><%= board.getCnt() %></td> --%>
		<td align="left">${board.cnt}</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="�� ����"/>
		</td>
	</tr>
</table>
</form>
<hr>
<a href="insertBoard.jsp">�۵��</a>&nbsp;&nbsp;&nbsp;
<%-- <a href="deleteBoard_proc.jsp?seq=<%= board.getSeq() %>">�ۻ���</a>&nbsp;&nbsp;&nbsp; --%>
<%-- <a href="deleteBoard.do?seq=<%= board.getSeq() %>">�ۻ���</a>&nbsp;&nbsp;&nbsp; --%>
<a href="deleteBoard.do?seq=${board.seq}">�ۻ���</a>&nbsp;&nbsp;&nbsp;
<!-- <a href="getBoardList.jsp">�۸��</a> -->
<a href="getBoardList.do">�۸��</a>
</center>
</body>
</html>	
	