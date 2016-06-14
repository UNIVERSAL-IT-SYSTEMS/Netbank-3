<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Put-in</title>
</head>
<body>
	<%
		UUID id = (UUID) session.getAttribute("cusID");
	%>
	<h5><%=id%></h5>
	<%
		if (session == null || session.getAttribute("cusID") == null) {
			response.sendRedirect("/Netbank/index.jsp");
		}
	%>
	<form action="ChangePasswordServlet" method="post">
		<input type="password" name="oldpassword" placeholder="Old password"
			required> <input type="password" name="newpassword"
			placeholder="New password" required> <input type="password"
			name="repeatednewpassword" placeholder="Repeat new password" required>
		<input type="hidden" name="cusID" value="<%=id%>" /> <input
			type="submit" name="login">
	</form>
	<form name="Menu" action="MainMenu.jsp">
		<input type="submit" value="Back to Menu" />
	</form>
</body>
</html>