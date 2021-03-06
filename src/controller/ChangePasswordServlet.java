package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import netbank.*;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("cusID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String old = request.getParameter("oldpassword");
			String newpass = request.getParameter("newpassword");
			String repeat = request.getParameter("repeatednewpassword");
			String id = request.getParameter("cusID");
			try {
				UserInf user = DatabaseGet.getUserByUserID(UUID.fromString(id));
				if (Dao.loginValidate(user.getSalt(), user.getHash(), old) && newpass.equals(repeat)) {
					Customer.ChangePassword(user, newpass);
					out.print("<h1>Success!</h1>");
					out.print("<a href=\"MainMenu.jsp\">Back</a>");
					out.close();
				} else {
					out.print("<h1>Failed</h1>");
					out.print("<a href=\"ChangePassword.jsp\">Back</a>");
					out.close();
				}
			} catch (Exception e) {
				out.print("<h1>Failed</h1>");
				out.print("<a href=\"ChangePassword.jsp\">Back</a>");
				out.close();
			}
		}

	}
}
