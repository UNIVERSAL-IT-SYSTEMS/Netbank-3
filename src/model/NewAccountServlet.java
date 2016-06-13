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

import netbank.UserInf;
import netbank.DatabaseGet;
import netbank.Employee;
import netbank.IDType;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cusid=request.getParameter("cusid");
		String interest=request.getParameter("interest");
		String currency=request.getParameter("currency");
		String empid=request.getParameter("empid");
		
		UserInf cust = DatabaseGet.getCustomer(IDType.CUSID, UUID.fromString(cusid));
		
		Employee.newAccount(cust, Double.valueOf(interest), Currency.getInstance(currency));
		
		request.setAttribute("empid", empid);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
		dispatcher.forward(request, response);
	}

}
