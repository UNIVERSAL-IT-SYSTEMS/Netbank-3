<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.UUID"%>
<%@page import="netbank.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String id=request.getParameter("updateinformation"); %>
<% ArrayList<Account> accounts = DatabaseGet.getAccounts(IDType.ACCID, UUID.fromString(id)); %>
<form action="ChangeInformation.jsp">
	<table border="1" style="width:100%">
		<tr>
			<td> Account ID </td>
			<td> Balance </td>
			<td> Currency </td>
			<td> Debt </td>
			<td> Interest </td>
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
			</tr>
			<tr>
				<td> Can't change </td>
				<td> Can't change </td>
				<td> <input type="text" name="currency" placeholder="new Currency"> </td>
				<td> Can't change </td>
				<td> <input type="text" name="Interest" placeholder="new Interest"> </td>
			</tr>
		<% } %>
	<% } %>
	</table>
	<input type="submit" name="Change information">
</form>
</body>
</html>