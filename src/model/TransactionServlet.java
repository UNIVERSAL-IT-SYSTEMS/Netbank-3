package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("cusID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String choice = request.getParameter("choice");
		String amount = request.getParameter("amount");
		String receiverID = request.getParameter("receiverID");
		System.out.println("HAVE INFO - TRYING TO DO TRANSACTION");
		if (Dao.Transaction(choice, amount, receiverID)) {
			out.print("<h1>Success!</h1>");
			out.print("<a href=\"MainMenu.jsp\">Back</a>");
			out.close();
		} else {
			out.print("<h1>Failed</h1>");
			out.print("<a href=\"Transaction.jsp\">Back</a>");
			out.close();
		}
	}
}
