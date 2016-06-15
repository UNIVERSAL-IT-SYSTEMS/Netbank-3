package model;

import java.io.IOException;
import java.sql.SQLException;
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

		// Check if username is not already taken
		// CustomerInf sameUsername = DatabaseGet.getCustomer(username);
		if (Dao.userNameExists(username)) {// sameUsername != null) {
			request.setAttribute("message", "Username taken");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		} else if (!password.equals(repeatpassword)) {
			request.setAttribute("message", "Passwords not matching");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		} else {
			String salt = Hash.getSalt();
			DatabaseSet.setUser(new UserInf(UUID.randomUUID(), username, name, address, language, country, salt,
					Hash.SHA512(password, salt), false));
			request.setAttribute("message", "Registered");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		// doGet(request, response);
		// out.close();
	}

}
