<!DOCTYPE HTML>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Currency"%>
<%@page import="netbank.*" %>
<%@page import="java.text.NumberFormat" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale" %>
<%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Put-in Bank</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Put-in Bank</h1>
<% Employee employee = new Employee(new Person("Morten Nielsen", "DTU", "dk", "DK")); %>
<% employee.newAccount(50000.9999, employee.getPersonName(), 2.0, 50000.0, Currency.getInstance(Locale.CHINA)); %>
<h2>

Hello <%= employee.getPersonName() %>. You live in <%= employee.getPersonLocation().getDisplayCountry() %>, <%= employee.getPersonAddress() %>
</h2>
<h3>
<%= employee.getCurrentEchangeRate(Currency.getInstance(Locale.UK)) %> and  
<%= employee.changeCurrency(Currency.getInstance(Locale.GERMANY), Currency.getInstance(Locale.US)) %>
</h3>
<h4><% String salt = Hash.getSalt(); %><%= Hash.SHA512("morten",salt) %></h4>


<select>
	<%
	Object[] currencies = Currency.getAvailableCurrencies().toArray();  
	for(int i = 0; i < currencies.length; i++) {
		String option = currencies[i].toString();
	%>
	<option value="<%= option %>"><%= option %></option>
	<% } %>
</select>

<select>
	<%
	Object[] languages = Locale.getISOLanguages();  
	for(int i = 0; i < languages.length; i++) {
		String option = languages[i].toString();
	%>
	<option value="<%= option %>"><%= option %></option>
	<% } %>
</select>

<select>
	<%
	Object[] regions = Locale.getISOCountries();  
	for(int i = 0; i < regions.length; i++) {
		String option = regions[i].toString();
	%>
	<option value="<%= option %>"><%= option %></option>
	<% } %>
</select>

</body>
</html>