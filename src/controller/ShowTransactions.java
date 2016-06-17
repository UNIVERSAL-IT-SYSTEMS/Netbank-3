package controller;

import netbank.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowTransactions
 */
@WebServlet("/ShowTransactions")
public class ShowTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("cusID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String accid = request.getParameter("showtransactions");
		ArrayList<Transaction> transactions = DatabaseGet.getTransactionByAccountID(UUID.fromString(accid));
		out.println("<table border=\"1\" style=\"width:100%\">");
		out.println("<tr>" + "<td>Amount</td>" + "<td>Currency</td>" + "<td>ReceiverID</td>" + "<td>SenderID</td>"
				+ "<td>Timestamp</td>" + "<td>TransactionID</td>" + "<td>TransactionType</td>" + "</tr>");
		for (int i = 0; i < transactions.size(); i++) {
			out.println("<tr>" + "<td>" + transactions.get(i).getAmount() + "</td>" + "<td>"
					+ transactions.get(i).getCurrency() + "</td>" + "<td>" + transactions.get(i).getReceiverID()
					+ "</td>" + "<td>" + transactions.get(i).getSenderID() + "</td>" + "<td>"
					+ transactions.get(i).getTimestamp() + "</td>" + "<td>" + transactions.get(i).getTransactionID()
					+ "</td>" + "<td>" + transactions.get(i).getTransactionType() + "</td>" + "</tr>");
		}
		out.println("</table>");
		out.print("</form>" + "<form name=\"Menu\" action=\"MainMenu.jsp\">"
				+ "<input type=\"submit\" value=\"Back to Menu\" />" + "</form>");
		out.close();
	}

}
