package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WithdrawalServlet
 */
@WebServlet("/WithdrawalServlet")
public class WithdrawalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String choice=request.getParameter("choice");
		String amount=request.getParameter("amount");
		String custID=request.getParameter("custID");
		out.println(choice);
		out.println(amount);
		out.println(custID);
		System.out.println("HAVE INFO - TRYING TO DO WITHDRAWAL");
		if(Dao.Withdrawal(choice,amount)) {
			out.print("<h1>Success!</h1>");
			out.print("<% session.setAttribute(\"cusID\", "+custID+"); %>");
			out.print("<a href=\"MainMenu.jsp\">Back</a>");
			out.close();
		} else {
			out.print("<h1>Failed</h1>");
			out.print("<% session.setAttribute(\"cusID\", "+custID+"); %>");
			out.print("<a href=\"Withdrawal.jsp\">Back</a>");
			out.close();
		}
	}

}
