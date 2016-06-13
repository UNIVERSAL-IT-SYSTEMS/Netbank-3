<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="netbank.*"%>
<%@page import="java.util.ArrayList"%>
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
<div>
<%UUID id = (UUID) session.getAttribute("cusID"); %>
<%=id%>
<% ArrayList<Account> accounts = DatabaseGet.getAccounts(id); %>
<form action="WithdrawalServlet" method="get">
	<table border="1" style="width:100%">
		<tr>
			<td> Account ID </td>
			<td> Balance </td>
			<td> Currency </td>
			<td> Choice </td>
		</tr>
		<% NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA); %>
		<% if(accounts != null) { %>
			<% for ( int i =0; i < accounts.size(); i++) { %>
				<% numberFormat.setCurrency(accounts.get(i).getCurrency()); %>
				<tr>
					<td> <%=accounts.get(i).getAccountID().toString()%> </td>
					<td> <%=numberFormat.format(accounts.get(i).getBalance())%> </td>
					<td> <%=accounts.get(i).getCurrency().getDisplayName()%> </td>
					<td> <input type="radio" name="choice" value="<%=accounts.get(i).getAccountID()%>"> </td>
				</tr>
			<% } %>
		<% } %>
	</table>
	Amount: <input type="text" name="amount" placeholder="Amount"> <br/>
	<input type="hidden" name="custID" value="<%=id.toString()%>">
	<input type="submit" name="withdrawal">
</form>
<form name="Menu" action="MainMenu.jsp">
	<input type="hidden" name="cusID" value="<%=id%>"/>
	<input type="submit" value="Back to Menu" />
</form>
</div>
</body>
</html>