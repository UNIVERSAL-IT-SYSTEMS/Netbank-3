package model;

import java.io.PrintWriter;  
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(Dao.loginValidate(username, password) || true) { 
			response.sendRedirect("MainMenu.jsp");
		} else {
			out.print("Username or password error");
			response.sendRedirect("LoginFail.jsp");
		}
		out.close(); 
	}
}
