package model;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbank.Account;
import netbank.DatabaseGet;
import netbank.Employee;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amount=request.getParameter("amount");
		String accid=request.getParameter("accid");
		Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid));
		Double am = Double.valueOf(amount);
		System.out.println(am);
		if(am > 0) {
			System.out.println("over 0");
			Employee.deposit(account, am);
		}
		
		
		
		request.setAttribute("message", "Successfully deposited");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
		dispatcher.forward(request, response);
	}
}
