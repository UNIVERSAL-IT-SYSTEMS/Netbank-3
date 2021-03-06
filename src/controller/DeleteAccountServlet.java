package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import netbank.Account;
import netbank.DatabaseGet;
import netbank.Employee;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("empID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			String accid = request.getParameter("accid");

			try {
				Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid));
				if (Employee.deleteAccount(account)) {
					request.setAttribute("message", "Deleted account " + accid);
				} else {
					request.setAttribute("message", "Could not delete account " + accid);
				}
			} catch (Exception e) {
				request.setAttribute("message", "Could not delete account " + accid);
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
			dispatcher.forward(request, response);
		}
	}

}
