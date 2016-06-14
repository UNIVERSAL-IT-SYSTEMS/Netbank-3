<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.UUID"%>
<%@page import="java.util.Currency"%>
<%@page import="netbank.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Put-in</title>
</head>
<body>
<% String accid=request.getParameter("accid"); %>
<% UUID empid = (UUID) session.getAttribute("empID"); %>
<% if (session == null || session.getAttribute("empID") == null) response.sendRedirect("/Netbank/index.jsp");%>
<% Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid)); %>
<form action="ChangeInformationServlet" method="post">
	<table border="1" style="width:100%">
		<tr>
			<td> Account ID </td>
			<td> Balance </td>
			<td> Currency </td>
			<td> Debt </td>
			<td> Interest </td>
			<td> Owner ID </td>
		</tr>
		<% NumberFormat numberFormat = NumberFormat.getCurrencyInstance(); %>
		<% if(account != null) { %>
			<% numberFormat.setCurrency(account.getCurrency()); %>
			<tr>
				<td> <%=account.getAccountID().toString()%> </td>
				<td> <%=numberFormat.format(account.getBalance())%> </td>
				<td> <%=account.getCurrency().getDisplayName()%> </td>
				<td> <%=numberFormat.format(account.getDebt())%> </td>
				<td> <%=account.getInterest()%>% </td>
				<td> <%=account.getOwnerID() %> </td>
			</tr>
			<tr>
				<td> Can't change </td>
				<td> <input type="number" step="any" name="balance" placeholder="Subtract balance"> </td>
				<td> <select name="currency">
					<option value="dontchange" selected="selected"></option>
					<% Object[] currencies = DatabaseGet.getCurrencies().keySet().toArray(); %>
					<% for(int i = 0; i < currencies.length; i++) { %> 
						<option value="<%=currencies[i].toString()%>"><%=currencies[i].toString()%></option>
					<% } %>
				</select> </td>
				<td> <input type="number" step="any" name="debt" placeholder="Add/subtract debt"> </td>
				<td> <input type="number" step="any" name="interest" placeholder="New interest"> </td>
				<td> <input type="text" name="cusid" placeholder="New owner"> </td>
			</tr>
		<% } %>
	</table>
	<input type="hidden" name="accid" value="<%= account.getAccountID().toString() %>">
	<input type="submit" name="changeinformation">
</form>
</body>
</html>