package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbank.*;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String old=request.getParameter("oldpassword");
		String newpass=request.getParameter("newpassword");
		String repeat=request.getParameter("repeatednewpassword");
		String id= request.getParameter("cusID");
		
		out.println(newpass);
		out.println(repeat);
		out.println(id);
		UserInf cust = DatabaseGet.getCustomer(IDType.CUSID,UUID.fromString(id));
		if(Dao.loginValidate(cust.getSalt(), cust.getHash(), old) && newpass.equals(repeat)) {
			Customer.ChangePassword(cust, newpass);
			out.print("<h1>Success!</h1>");
			out.print("<% session.setAttribute(\"cusID\", "+id+"); %>");
			out.print("<a href=\"MainMenu.jsp\">Back</a>");
			out.close();
		} else {
			out.print("<h1>Failed</h1>");
			out.print("<% session.setAttribute(\"cusID\", "+id+"); %>");
			out.print("<a href=\"ChangePassword.jsp\">Back</a>");
			out.close();
		}
	}
}
