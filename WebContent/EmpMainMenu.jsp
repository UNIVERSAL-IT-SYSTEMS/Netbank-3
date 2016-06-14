<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="netbank.DatabaseGet"%>
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
<title>Put-in</title>
</head>
<body>
	<%
		UUID empid = (UUID) session.getAttribute("empID");
	%>
	<%
		if (session == null || session.getAttribute("empID") == null)
			response.sendRedirect("/Netbank/index.jsp");
	%>
	<h5><%=empid%></h5>
	<%
		if (request.getAttribute("message") != null) {
			out.println(request.getAttribute("message"));
		}
	%>
	<div>
		<h2>Search</h2>
		<form action="EmpAccounts.jsp">
			<input type="text" name="id" placeholder="ID" required> <input
				type="submit" name="search">
		</form>

		<h2>Deposit</h2>
		<form action="DepositServlet" method="post">
			<input type="number" step="any" name="amount" placeholder="Amount"
				required /> <input type="text" name="accid"
				placeholder="Account ID" required /> <input type="submit"
				name="search">
		</form>

		<h2>New Account</h2>
		<form action="NewAccountServlet" method="post">
			<input type="text" name="cusid" placeholder="Customer ID" required>
			<input type="number" step="any" name="interest"
				placeholder="Interest" required> <select name="currency">
				<%
					Object[] currencies = DatabaseGet.getCurrencies().keySet().toArray();
				%>
				<%
					for (int i = 0; i < currencies.length; i++) {
				%>
				<option value="<%=currencies[i].toString()%>"><%=currencies[i].toString()%></option>
				<%
					}
				%>
			</select> <input type="submit" name="newaccount">
		</form>

		<h2>Delete Account</h2>
		<form action="DeleteAccountServlet" method="post">
			<input type="text" name="accID" placeholder="Account ID" required>
			<input type="submit" name="deleteaccount">
		</form>

		<h2>Update Currencies</h2>
		<form action="UpdateCurrenciesServlet" method="post">
			<input type="submit" name="update">
		</form>

		<form action="LogoutServlet" method="post">
			<input type="submit" value="Logout" />
		</form>
	</div>
</body>
</html>