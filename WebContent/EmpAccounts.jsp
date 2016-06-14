<%@page import="java.util.Locale"%>
<%@page import="java.util.UUID"%>
<%@page import="netbank.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Put-in</title>
</head>
<body>
<% String id=request.getParameter("id"); %>
<% UUID empid = (UUID) session.getAttribute("empID"); %>
<% if (session == null || session.getAttribute("empID") == null) response.sendRedirect("/Netbank/index.jsp");%>
<%=id %>
<%=empid %>
<h2>Customer</h2>
<% UserInf cust = DatabaseGet.getUserByUserID(UUID.fromString(id)); %>

<table border="1" style="width:100%">
	<tr>
		<td> Customer ID </td>
		<td> Name </td>
		<td> Address </td>
		<td> Language </td>
		<td> Country </td>
		<td> Locale </td>
		<td> Username </td>
	</tr>
	<% if(cust != null) { %>
		<tr>
			<td> <%= cust.getID() %> </td>
			<td> <%= cust.getName() %> </td>
			<td> <%= cust.getAddress() %> </td>
			<td> <%= cust.getLanguage() %> </td>
			<td> <%= cust.getCountry() %> </td>
			<td> <%= cust.getLocale() %> </td>
			<td> <%= cust.getUsername() %> </td>
		</tr>
	<% } %>
</table>

<h2>Accounts</h2>
<% ArrayList<Account> accounts = DatabaseGet.getAccountsByUserID(UUID.fromString(id)); %>
<% Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(id)); %>
<% if(account!=null) { accounts.add(account); } %>
<form action="UpdateInformation.jsp">
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
					<td> <button name="accid" value="<%=accounts.get(i).getAccountID()%>">Update information</button> </td>
				</tr>
			<% } %>
		<% } %>
	</table>
</form>

<% ArrayList<Transaction> transactions = DatabaseGet.getTransactionByAccountID(UUID.fromString(id)); %>
<h2>Transactions</h2>
<table border="1" style="width:100%">
	<tr>
		<td>Amount</td>
		<td>Currency</td>
		<td>ReceiverID</td>
		<td>SenderID</td>
		<td>Timestamp</td>
		<td>TransactionID</td>
		<td>TransactionType</td>
	</tr>
	<% if(transactions != null) { %>
		<% for(int i = 0; i<transactions.size(); i++) { %>
			<tr>
				<td><%= transactions.get(i).getAmount() %></td>
				<td><%= transactions.get(i).getCurrency() %></td>
				<td><%= transactions.get(i).getReceiverID() %></td>
				<td><%= transactions.get(i).getSenderID() %></td>
				<td><%= transactions.get(i).getTimestamp() %></td>
				<td><%= transactions.get(i).getTransactionID() %></td>
				<td><%= transactions.get(i).getTransactionType() %></td>
			</tr>
		<% } %>
	<% } %>
</table>
<form name="Menu" action="EmpMainMenu.jsp">
	<input type="submit" value="Back to Menu" />
</form>

</body>
</html>