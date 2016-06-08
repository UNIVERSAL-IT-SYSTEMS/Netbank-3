<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="netbank.*" %>
<%@page import="model.*" %>

<%@page import="java.sql.ResultSet" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div class="login-screen">
			<h1>SQL</h1><br>
			<a href="index.jsp">Back</a>
			<form method="post" action="servle">
				<input type="text" name="user" placeholder="Query">
				<input type="submit" name="sqldo" class="login-submit" value="Do">
			</form>
    	</div>
    	Sample: <br>
    	<% if(servle.getDb() == null) { servle.initDB(); } %>
    	<% ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.Sample"); %>
    	<table>
			<% while(res.next()) { %>
			<tr>
				<td><%= res.getString(1) %></td>
				<td><%= res.getString(2) %></td>
				<td><%= res.getString(3) %></td>
			</tr>
			<% } %>
		</table>
		<br><br>Users<br>
		<% res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\""); %>
		<table>
			<% while(res.next()) { %>
			<tr>
				<td><%= res.getString(1) %></td>
				<td><%= res.getString(2) %></td>
				<td><%= res.getString(3) %></td>
				<td><%= res.getString(4) %></td>
		</tr>
		<% } %>
		</table>
		<br><br>Accounts<br>
		<% res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"accounts\""); %>
		<table>
			<% while(res.next()) { %>
			<tr>
				<td><%= res.getString(1) %></td>
				<td><%= res.getString(2) %></td>
				<td><%= res.getString(3) %></td>
				<td><%= res.getString(4) %></td>
				<td><%= res.getString(5) %></td>
				<td><%= res.getString(6) %></td>
			</tr>
			<% } %>
		</table>

</body>
</html>