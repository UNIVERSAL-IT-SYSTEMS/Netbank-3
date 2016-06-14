<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="netbank.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Currency"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<!DOCTYPE html">
<html>
<head>
	<title>Put-in</title>
	<link rel='stylesheet' href=''>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<% UUID cusid = (UUID) session.getAttribute("cusID"); %>
<% if (session ==  null ) response.sendRedirect("/Netbank/index.jsp");%>
<% if(session.getAttribute("cusID")==null) response.sendRedirect("/Netbank/index.jsp");%>
	<div style="text-align: center">
		<% ArrayList<Account> accounts = DatabaseGet.getAccountsByUserID(cusid); %>
		<% UserInf cust = DatabaseGet.getUserByUserID(cusid); %>
		<h2><%= cust.getName() %></h2>
		<h5><%= cust.getID() %></h5>
		<%=new Timestamp(new java.util.Date().getTime()).toString().substring(0,23) %><br>
		<form action="ShowTransactions" method="post">
			<table border="1" style="width:100%">
				<tr>
					<td> Account ID </td>
					<td> Balance </td>
					<td> Currency </td>
					<td> Debt </td>
					<td> Interest </td>
					<td>  </td>
				</tr>
			<% NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA); %>
			<% if(accounts != null) { %>
				<% for ( int i =0; i < accounts.size(); i++) { %>
					<% numberFormat.setCurrency(accounts.get(i).getCurrency()); %>
					<tr>
						<td> <%=accounts.get(i).getAccountID().toString()%> </td>
						<td> <%=numberFormat.format(accounts.get(i).getBalance())%> </td>
						<td> <%=accounts.get(i).getCurrency().getDisplayName()%> </td>
						<td> <%=numberFormat.format(accounts.get(i).getDebt())%> </td>
						<td> <%=accounts.get(i).getInterest()%>% </td>
						<td> <button name="showtransactions" value="<%=accounts.get(i).getAccountID()%>">Transactions</button> </td>
					</tr>
				<% } %>
			<% } %>
			</table>
		</form>
			
		<form action="Transaction.jsp">
			<input type="submit" name="transaction" value="Transaction">		
		</form>
		<form action="Withdrawal.jsp">
			<button name="withdrawal" type="submit">Withdrawal</button>
		</form>
		<form action="ChangePassword.jsp">
			<button name="changepassword" type="submit">Change password</button>
		</form>
		<a href="index.jsp">Back to log-in</a>
		
	</div>
</body>
</html>