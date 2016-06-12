<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="netbank.IDType"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% UUID empid = (UUID) request.getAttribute("empID"); %>
<%  %>
<h2>Search</h2>
<form action="EmpAccounts" method="post">
	<select name="IDType">
		<% for(int i = 0; i < IDType.values().length; i++) { %> 
			<option value="<%=IDType.values()[i]%>"><%=IDType.values()[i]%></option>
		<% } %>
	</select>
	<input type="text" name="id" placeholder="ID">
	<input type="hidden" name="empid" value="<%=empid%>"/>
	<input type="submit" name="search">
</form>

<h2>Update interest</h2>
<form action="UpdateInterestServlet" method="post">
	<input type="hidden" name="empid" value="<%=empid%>"/>
	<input type="submit" name="search">		
</form>

<h2>Deposit</h2>
<form action="DepositServlet" method="post">
	<input type="text" name="amount" placeholder="Amount"/>
	<input type="text" name="accid" placeholder="Account ID"/>
	<input type="hidden" name="empid" value="<%=empid%>"/>
	<input type="submit" name="search">		
</form>

</body>
</html>