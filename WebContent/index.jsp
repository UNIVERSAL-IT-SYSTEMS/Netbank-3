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
	<div style="text-align: center">
		<div class="login-screen">
			<h1>Put-in</h1><br>
			<form action="login" method="post">
			<input type="text" name="username" placeholder="Username">
			<input type="password" name="password" placeholder="Password">
			<input type="submit" name="login">			
			</form>
    		<div class="login-options">
    			<a href="Register.jsp">Register</a> • <a href="#">Forgot Password</a>
    		</div>
    	</div>
		<% Currencies.UpdateCurrencies(); %>
		<%= Currencies.getCurrency(Currency.getInstance(Locale.CHINA)) %>
		<br>
		<a href="tableview.jsp">View tables</a>
	</div>
    	
	</body>
</html>