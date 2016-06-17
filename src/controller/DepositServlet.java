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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("empID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		String amount = request.getParameter("amount");
		String accid = request.getParameter("accid");

		try {
			Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid));
			Double am = Double.valueOf(amount);
			if (am > 0) {
				if (Employee.deposit(account, am)) {
					request.setAttribute("message", "Successfully deposited");
				} else {
					request.setAttribute("message", "Deposit failed");
				}
			} else {
				request.setAttribute("message", "Only positive numbers accepted");
			}
		} catch (Exception e) {
			request.setAttribute("message", "Deposit failed");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
		dispatcher.forward(request, response);
	}
}
