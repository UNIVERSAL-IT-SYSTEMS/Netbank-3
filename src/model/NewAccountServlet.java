package model;

import java.io.IOException;
import java.util.Currency;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import netbank.UserInf;
import netbank.DatabaseGet;
import netbank.Employee;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("empID") == null) {
			// Forward the control to login.jsp if authentication fails or session expires
			request.getRequestDispatcher("Netbank/index.jsp").forward(request,response);
		}
		String cusid=request.getParameter("cusid");
		String interest=request.getParameter("interest");
		String currency=request.getParameter("currency");
		
		UserInf cust = DatabaseGet.getUserByUserID(UUID.fromString(cusid));
		
		Employee.newAccount(cust, Double.valueOf(interest), Currency.getInstance(currency));
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
		dispatcher.forward(request, response);
	}

}
