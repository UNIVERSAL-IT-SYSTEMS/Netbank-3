<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@page import="java.util.Locale"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Put-in</title>
</head>
<body>

<div class="register-screen" style="text-align: center">
	<h1>Register</h1><br>
	<% if(request.getAttribute("message")!=null){out.println(request.getAttribute("message"));} %>
		<form action="RegisterServlet" method="post">
			<input type="text" name="username" placeholder="Username" required><br/>
			<input type="password" name="password" placeholder="Password" required><br/>
			<input type="password" name="repeatpassword" placeholder="Repeat password" required><br/>
			<input type="text" name="name" placeholder="Name" required><br/>
			<input type="text" name="address" placeholder="Address" required><br/>
			Language: <select name="language">
				<% for(int i = 0; i < Locale.getISOLanguages().length; i++) { %> 
					<option value="<%=Locale.getISOLanguages()[i].toString()%>"><%=Locale.getISOLanguages()[i].toString()%></option>
				<% } %>
			</select><br/>
			Country: <select name="country">
				<% for(int i = 0; i < Locale.getISOCountries().length; i++) { %> 
					<option value="<%=Locale.getISOCountries()[i].toString()%>"><%=Locale.getISOCountries()[i].toString() %></option>
				<% } %>
			</select><br/>
			<input type="submit" name="RegisterServlet">
		</form>
    <div class="login-options">
    	<a href="index.jsp">Go back</a>
   	</div>
</div>

</body>
</html>