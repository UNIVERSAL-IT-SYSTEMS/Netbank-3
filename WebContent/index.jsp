<!DOCTYPE HTML>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Currency"%>
<%@page import="netbank.*" %>
<%@page import="java.math.BigDecimal" %>
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
<% employee.newAccount(BigDecimal.valueOf(50000.0), employee.getPersonName(), BigDecimal.valueOf(2.0), BigDecimal.valueOf(50000.0), Currency.getInstance(Locale.CHINA)); %>
<h2>

Hello <%= employee.getPersonName() %>. You live in <%= employee.getPersonLocation().getDisplayCountry() %>, <%= employee.getPersonAddress() %>
</h2>
<h3>
<%= employee.getCurrentEchangeRate(Currency.getInstance(Locale.UK)) %> and <%= employee.change(BigDecimal.valueOf(1234.99999)) %>, 
<%= employee.changeCurrency(Currency.getInstance(Locale.UK), Currency.getInstance(Locale.CHINA)) %>
</h3>
</body>
</html>