<%@page import="java.util.Currency"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% UUID empid = (UUID) request.getAttribute("empID"); %>

<h2>Search</h2>
<form action="EmpAccounts.jsp">
	<input type="text" name="id" placeholder="ID">
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

<h2>New Account</h2>
<form action="NewAccountServlet" method="post">
	<input type="text" name="cusid" placeholder="Customer ID">
	<input type="text" name="interest" placeholder="Interest">
	<select name="currency">
		<% for(int i = 0; i < Currency.getAvailableCurrencies().toArray().length; i++) { %> 
			<option value="<%=Currency.getAvailableCurrencies().toArray()[i]%>"><%=Currency.getAvailableCurrencies().toArray()[i]%></option>
		<% } %>
	</select>
	<input type="hidden" name="empid" value="<%=empid%>"/>
	<input type="submit" name="newaccount">
</form>

<h2>Delete Account</h2>
<form action="DeleteAccountServlet" method="post">
	<input type="text" name="accID" placeholder="Account ID">
	<input type="hidden" name="empid" value="<%=empid%>"/>
	<input type="submit" name="deleteaccount">
</form>

</body>
</html>