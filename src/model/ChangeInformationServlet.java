package model;

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
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("empID") == null) {
			// Forward the control to login.jsp if authentication fails or session expires
			request.getRequestDispatcher("Netbank/index.jsp").forward(request,response);
		}
		
		String accid=request.getParameter("accid");
		String balance=request.getParameter("balance");
		String currency=request.getParameter("currency");
		String debt=request.getParameter("debt");
		String interest=request.getParameter("interest");
		String cusid=request.getParameter("cusid");
		System.out.println("accid = "+accid);
		System.out.println("cusid = "+cusid);
		Account account = DatabaseGet.getAccountByAccountID(UUID.fromString(accid));
		if(!balance.isEmpty()) {
			setAccountBalance(Double.valueOf(balance), account);
		}
		if(!interest.isEmpty()) {
			setAccountInterest(Double.valueOf(interest), account);
		}
		if(currency != account.getCurrency().getCurrencyCode()) {
			setAccountCurrency(Currency.getInstance(currency),account);
		}
		if(!debt.isEmpty()) {
			setAccountDebt(Double.valueOf(debt),account);
		}
		if(!cusid.isEmpty()) {
			changeAccountOwner(account, UUID.fromString(cusid));
		}
	}

	private void setAccountBalance(Double balance, Account account) {
		Employee.subtractAccountBalance(account, balance);
	}

	private void setAccountInterest(Double interest, Account account) {
		Employee.setAccountInterest(account, interest);
	}
	
	private void setAccountCurrency(Currency currency, Account account) {
		Employee.setAccountCurrency(account, currency);
	}
	
	private void setAccountDebt(Double amount, Account account) {
		if(amount < 0) {
			Employee.subtractAccountDebt(account, amount);
		} else {
			Employee.addAccountDebt(account, amount);
		}
	}

	private void changeAccountOwner(Account account, UUID id) {
		Employee.changeOwnershipOfAccount(account, id);
	}

}
