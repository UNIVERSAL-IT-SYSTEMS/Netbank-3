<!DOCTYPE HTML>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Currency"%>
<%@page import="netbank.*"%>
<%@page import="model.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Put-in</title>
</head>
<body>
	<div style="text-align: center">
		<div class="login-screen">
			<h1>Put-in</h1>
			<br>
			<%
				if (request.getAttribute("message") != null) {
					out.println(request.getAttribute("message"));
				}
			%>
			<form action="login" method="post">
				<input type="text" name="username" placeholder="Username" required>
				<input type="password" name="password" placeholder="Password"
					required> <input type="submit" name="login">
			</form>
			<div class="login-options">
				<a href="Register.jsp">Register</a>
			</div>
		</div>
	</div>
</body>
</html>