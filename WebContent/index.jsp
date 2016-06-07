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
<%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Log-in</title>
		<link rel='stylesheet' href=''>
	</head>
	<body>
		<div class="login-screen">
			<h1>Log-in</h1><br>
			<form>
			<input type="text" name="user" placeholder="Username">
			<input type="password" name="pass" placeholder="Password">
			<input type="submit" name="login" class="login-submit" value="login">
			</form>
    		<div class="login-options">
    			<a href="#">Register</a> • <a href="#">Forgot Password</a>
    		</div>
    	</div>
    	<% Currencies.UpdateCurrencies(); %> <%= Currencies.getCurrency(Currency.getInstance(Locale.CHINA)) %>
    	
    	<div class="login-screen">
			<h1>SQL</h1><br>
			<form>
			<input type="text" name="user" placeholder="Query">
			<input type="submit" name="sqldo" class="login-submit" value="Do">
			</form>
    	</div>
    	Sample: <br>
    	<% ResultSet res = servle.getDb().getters("SELECT * FROM DTUGRP04.Sample"); %>
			<% while(res.next()) { %>
				<%= res.getString(1) %>
				<%= res.getString(2) %>
			<% } %>
		<br><br>Users<br>
		<% res = servle.getDb().getters("SELECT * FROM DTUGRP04.\"customers\""); %>
			<% while(res.next()) { %>
				<%= res.getString(1) %>
				<%= res.getString(2) %>
			<% } %>
		<br><br>Accounts<br>
		<% res = servle.getDb().getters("SELECT * FROM DTUGRP04.Sample"); %>
			<% while(res.next()) { %>
				<%= res.getString(1) %>
				<%= res.getString(2) %>
			<% } %>
	</body>
</html>