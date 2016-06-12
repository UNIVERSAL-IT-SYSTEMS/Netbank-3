package model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbank.Employee;

/**
 * Servlet implementation class UpdateInterestServlet
 */
@WebServlet("/UpdateInterestServlet")
public class UpdateInterestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empid=request.getParameter("empID");
		
		Employee.updateInterest();
		
		request.setAttribute("message", "Updated interests");
		request.setAttribute("empID", empid);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("EmpMainMenu.jsp");
		dispatcher.forward(request, response);
		
	}

}
