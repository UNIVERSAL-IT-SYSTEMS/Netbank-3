<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Locale"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>

<div class="register-screen" style="text-align: center">
	<h1>Register</h1><br>
	<% if(request.getAttribute("message")!=null){out.println(request.getAttribute("message"));} %>
		<form action="RegisterServlet" method="post">
			<input type="text" name="username" placeholder="Username"><br/>
			<input type="password" name="password" placeholder="Password"><br/>
			<input type="password" name="repeatpassword" placeholder="Repeat password"><br/>
			<input type="text" name="name" placeholder="Name"><br/>
			<input type="text" name="address" placeholder="Address"><br/>
			Language: <select name="language">
				<% for(int i = 0; i < Locale.getISOLanguages().length; i++) { %> 
					<option value="<%=i%>"><%=Locale.getISOLanguages()[i]%></option>
				<% } %>
			</select><br/>
			Country: <select name="country">
				<% for(int i = 0; i < Locale.getISOCountries().length; i++) { %> 
					<option value="<%=i%>"><%=Locale.getISOCountries()[i] %></option>
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