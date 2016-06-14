package model;

import netbank.*;
import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servle.initDB();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserInf user = DatabaseGet.getUserByUsername(username);
		if (user != null && Dao.loginValidate(user.getSalt(), user.getHash(), password)) {
			if (user.getIsEmployee()) {
				HttpSession session = request.getSession();
				session.setAttribute("empID", user.getID());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
				dispatcher.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("cusID", user.getID());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("MainMenu.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Username or password error");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		out.close();
	}
}
