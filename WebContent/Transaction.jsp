<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.UUID"%>
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
	<div>
		<%
			UUID id = (UUID) session.getAttribute("cusID");
		%>
		<%
			if (session == null || session.getAttribute("cusID") == null)
				response.sendRedirect("/Netbank/index.jsp");
		%>
		<%=id%>
		<%
			ArrayList<Account> accounts = DatabaseGet.getAccountsByUserID(id);
		%>
		<form action="TransactionServlet" method="get">
			<table border="1" style="width: 100%">
				<tr>
					<td>Account ID</td>
					<td>Balance</td>
					<td>Currency</td>
					<td>Choice</td>
				</tr>
				<%
					NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
				%>
				<%
					if (accounts != null) {
				%>
				<%
					for (int i = 0; i < accounts.size(); i++) {
				%>
				<%
					numberFormat.setCurrency(accounts.get(i).getCurrency());
				%>
				<tr>
					<td><%=accounts.get(i).getAccountID().toString()%></td>
					<td><%=numberFormat.format(accounts.get(i).getBalance())%></td>
					<td><%=accounts.get(i).getCurrency().getDisplayName()%></td>
					<td><input type="radio" name="choice"
						value="<%=accounts.get(i).getAccountID()%>"></td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
			</table>
			Amount: <input type="number" step="any" name="amount"
				placeholder="Amount" required> <br /> To: <input type="text"
				name="receiverID" placeholder="ID" required> <br /> <input
				type="submit" name="transaction">
		</form>
		<form name="Menu" action="MainMenu.jsp">
			<input type="submit" value="Back to Menu" />
		</form>
	</div>
</body>
</html>