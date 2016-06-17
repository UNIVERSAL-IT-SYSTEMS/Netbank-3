package controller;

import java.io.IOException;
import java.util.Currency;
import java.util.UUID;

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
 * Servlet implementation class ChangeInformationServlet
 */
@WebServlet("/ChangeInformationServlet")
public class ChangeInformationServlet extends HttpServlet {
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
			String balance = request.getParameter("balance");
			String currency = request.getParameter("currency");
			String debt = request.getParameter("debt");
			String interest = request.getParameter("interest");
			String cusid = request.getParameter("cusid");
			try {
				Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid));
				String returnMessage = "";

				if (setAccountBalance(balance, account)) {
					returnMessage += "balance, ";
				}

				if (setAccountInterest(interest, account)) {
					returnMessage += "interest, ";
				}

				if (setAccountCurrency(currency, account)) {
					returnMessage += "currency, ";
				}

				if (setAccountDebt(debt, account)) {
					returnMessage += "debt, ";
				}

				if (changeAccountOwner(account, cusid)) {
					returnMessage += "cusid, ";
				}

				if (returnMessage.isEmpty()) {
					request.setAttribute("message", "Wasn't able to change anything");
				} else {
					request.setAttribute("message", "Was able to change " + returnMessage);
				}
				request.getRequestDispatcher("EmpMainMenu.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("message", "Something went wrong");
				request.getRequestDispatcher("EmpMainMenu.jsp").forward(request, response);
			}
		}

	}

	private boolean setAccountBalance(String balance, Account account) {
		try {
			if (!balance.isEmpty()) {
				Double BalanceDouble = Double.parseDouble(balance);
				return Employee.subtractAccountBalance(account, BalanceDouble);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean setAccountInterest(String interest, Account account) {
		try {
			if (!interest.isEmpty()) {
				Double interestDouble = Double.parseDouble(interest);
				return Employee.setAccountInterest(account, interestDouble);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean setAccountCurrency(String currency, Account account) {
		try {
			if (currency != account.getCurrency().getCurrencyCode() || currency != "dontchange") {
				Currency currencyObject = Currency.getInstance(currency);
				return Employee.setAccountCurrency(account, currencyObject);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean setAccountDebt(String debt, Account account) {
		try {
			if (!debt.isEmpty()) {
				Double debtDouble = Double.parseDouble(debt);
				if (debtDouble < 0) {
					// The user will input a negative number, but it's
					// calculated as positive
					debtDouble = -debtDouble;
					return Employee.subtractAccountDebt(account, debtDouble);
				} else {
					return Employee.addAccountDebt(account, debtDouble);
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean changeAccountOwner(Account account, String id) {
		try {
			if (!id.isEmpty()) {
				UUID cusid = UUID.fromString(id);
				return Employee.changeOwnershipOfAccount(account, cusid);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
