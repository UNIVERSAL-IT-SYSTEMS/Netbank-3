<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="netbank.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Currency"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
</head>
<body>
<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts"); %>
<% request.getAttribute("customer"); %>
<% session.setAttribute("accounts", accounts); %>
<% session.setAttribute("customer", "customer"); %>


<table border="1" style="width:100%">
	<tr>
		<td> Account ID </td>
		<td> Balance </td>
		<td> Currency </td>
		<td> Debt </td>
		<td> Interest </td>
		<td> Owner ID </td>
	</tr>
<% NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA); %>
<% for ( int i =0; i < accounts.size() ; i++) { %>
	<% numberFormat.setCurrency(accounts.get(i).getCurrency()); %>
	<tr>
		<td> <%=accounts.get(i).getAccountID().toString()%> </td>
		<td> <%=numberFormat.format(accounts.get(i).getBalance())%> </td>
		<td> <%=accounts.get(i).getCurrency().getDisplayName()%> </td>
		<td> <%=numberFormat.format(accounts.get(i).getDebt())%> </td>
		<td> <%=accounts.get(i).getInterest()%>% </td>
		<td> <%=accounts.get(i).getOwnerID()%> </td>
	</tr>
<% } %>
</table>

<a href="Transaction.jsp">
   <input type="button" value="Transaction" />
   <% session.setAttribute("username", accounts); %>
   <% session.setAttribute("customer", "customer"); %>
</a>
<form action="WithdrawalServlet" method="post">
	<button name="withdrawal" type="submit">Withdrawal</button>
</form>
<form action="ChangePasswordServlet" method="post">
	<button name="changepassword" type="submit">Change password</button>
</form>
<div class="login-options">
	<a href="index.jsp">Back to log-in</a>
</div>

</body>
</html>