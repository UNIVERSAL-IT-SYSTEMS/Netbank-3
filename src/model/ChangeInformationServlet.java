package model;

import java.io.IOException;
import java.util.Currency;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String accid=request.getParameter("accid");
		String balance=request.getParameter("balance");
		String currency=request.getParameter("currency");
		String debt=request.getParameter("debt");
		String interest=request.getParameter("interest");
		String cusid=request.getParameter("cusid");
		Account account = DatabaseGet.getAccounts(UUID.fromString(accid)).get(0);
		if(balance!=null) {
			setAccountBalance(Double.valueOf(balance), account);
		}
		if(interest!=null) {
			setAccountInterest(Double.valueOf(interest), account);
		}
		if(currency!=null) {
			setAccountCurrency(Currency.getInstance(currency),account);
		}
		if(debt!=null) {
			setAccountDebt(Double.valueOf(debt),account);
		}
		if(cusid!=null) {
			changeAccountOwner(account, UUID.fromString(cusid));
		}
	}

	private void setAccountBalance(Double balance, Account account) {
		Employee.subtractAccountBalance(account, balance);
	}

	private void setAccountInterest(Double interest, Account account) {
		Employee.setAccountInterest(account, interest);
	}
	
	private boolean setAccountCurrency(Currency currency, Account account) {
		return Employee.setAccountCurrency(account, currency);
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
