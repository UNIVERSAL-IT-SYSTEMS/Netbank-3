package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import netbank.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeatpassword = request.getParameter("repeatpassword");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String language = request.getParameter("language");
		String country = request.getParameter("country");

		try {
			if (Dao.userNameExists(username)) {
				request.setAttribute("message", "Username taken");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Register.jsp");
				dispatcher.forward(request, response);
			} else if (!password.equals(repeatpassword)) {
				request.setAttribute("message", "Passwords not matching");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Register.jsp");
				dispatcher.forward(request, response);
			} else {
				if(Customer.registerUser(username, name, address, language, country, password)) {
					request.setAttribute("message", "Registered");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", "Something went wrong!");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("message", "Something went wrong!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
