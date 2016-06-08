<!DOCTYPE HTML>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Currency"%>
<%@page import="netbank.*" %>
<%@page import="model.*" %>

<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="org.json.*;" %>
<%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Log-in</title>
		
		<link rel='stylesheet' href=''>
	</head>
	<body>
		<div class="login-screen">
		<% Currencies.UpdateCurrencies(); %>
		<%= Currencies.getCurrency(Currency.getInstance(Locale.CHINA)) %>
		<% if(request.getAttribute("message")!=null){out.println(request.getAttribute("message"));} %>
			<h1>Log-in</h1><br>
			<form action="login" method="post">
			<input type="text" name="username" placeholder="Username">
			<input type="password" name="password" placeholder="Password">
			<input type="submit" name="login">			
			</form>
    		<div class="login-options">
    			<a href="Register.jsp">Register</a>
    		</div>
    	</div>
		
    	<div class="login-screen">
			<h1>SQL</h1><br>
			<form method="post" action="servle">
				<input type="text" name="user" placeholder="Query">
				<input type="submit" name="sqldo" class="login-submit" value="Do">
			</form>
    	</div>
    	Sample: <br>
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