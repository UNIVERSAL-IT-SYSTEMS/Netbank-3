package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import netbank.Currencies;

/**
 * Servlet implementation class UpdateCurrenciesServlet
 */
@WebServlet("/UpdateCurrenciesServlet")
public class UpdateCurrenciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("empID") == null) {
			// Forward the control to login.jsp if authentication fails or
			// session expires
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (Currencies.UpdateCurrencies()) {
			out.println("<h2>Success!</h2>");
			out.print("</form>" + "<form name=\"Menu\" action=\"EmpMainMenu.jsp\">"
					+ "<input type=\"submit\" value=\"Back to Menu\" />" + "</form>");
		} else {
			out.println("<h2>Failed!</h2>");
			out.print("</form>" + "<form name=\"Menu\" action=\"EmpMainMenu.jsp\">"
					+ "<input type=\"submit\" value=\"Back to Menu\" />" + "</form>");
		}

	}

}
