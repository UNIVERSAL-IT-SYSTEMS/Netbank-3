<%@page import="netbank.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transaction</title>
</head>
<body>
<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts"); %>
<div>
<form action="TransactionServlet" method="post">
	<% String number = (String) request.getAttribute("number"); %>
	<% out.println(number); %>
	<% int i; %>
	Account: <select name="language">
		<% for(i = 0; i < accounts.size(); i++) { %> 
			<option value="<%=i%>"><%=accounts.get(i).getAccountID()%></option>
		<% } %>
	</select><br/>
	Balance: <%= accounts.get(i).getBalance() %> <br/>
	Amount: <input type="text" name="amount" value="Amount"> <br/>
	To: <input type="text" name="receiverID" value="ID"> <br/>
	<input type="submit" name="transaction">
</form>
</div>
</body>
</html>