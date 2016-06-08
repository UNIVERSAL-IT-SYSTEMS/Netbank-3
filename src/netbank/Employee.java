package netbank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.sql.Timestamp;

public class Employee extends User {
	
	public void newAccount(CustomerInf customer, Double interest, Currency currency) {
		DatabaseSet.setAccount(new Account(UUID.randomUUID(), customer.getID(), 0.0, interest, 0.0, currency));
	}
	
	public void setCostumerName(CustomerInf customer, String name) { 
		customer.setName(name); 
		DatabaseSet.setCostumer(customer); 
	}
	
	public void setCostumerAddress(CustomerInf customer, String address) { 
		customer.setAddress(address); 
		DatabaseSet.setCostumer(customer); 
	}
	
	public void setCostumerLocation(CustomerInf customer, String language, String country) { 
		customer.setLanguage(language);
		customer.setCountry(country);
		DatabaseSet.setCostumer(customer);
	}
	
	public void setAccountInterest(Account account, Double interest) { 
		account.setInterest(interest); 
		DatabaseSet.setAccount(account);
	}
	
	public Boolean setAccountCurrency(Account account, Currency currency) { 
		if(account.setCurrency(currency)) {
			DatabaseSet.setAccount(account);
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean subtractAccountBalance(Account account, Double value) { 
		if(account.belowZero(value)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), null, value, account.getCurrency(), 
			TransactionType.Withdrawal, new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.subtractBalance(value); 
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public void addAccountDebt(Account account, Double value) { 
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(),account.getOwnerID(), null, value, account.getCurrency(), 
			TransactionType.AddDebt, new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.addDebt(value);
		DatabaseSet.setAccount(account);
	}
	
	public Boolean subtractAccpountDebt(Account account, Double value) { 
		if(account.belowZero(value)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), null, value, account.getCurrency(), 
			TransactionType.SubtractDebt, new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.subtractDebt(value); 
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public void deposit(Account account, Double amount) {
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), null, amount, account.getCurrency(), 
			TransactionType.Deposit, new Timestamp(Calendar.getInstance().getTime().getTime())));
		account.addBalance(amount);
		DatabaseSet.setAccount(account);
	}
	
	public void changeOwnershipOfAccount(Account account, UUID newOwner) {
		CustomerInf thisCustomer = DatabaseGet.getCustomer(IDType.cusID,newOwner);
		account.setOwnerID(thisCustomer.getID());
		DatabaseSet.setAccount(account);
	}
	
	public Boolean deleteAccount(Account account) {
		if (account.getBalance() == 0 && account.getDebt() == 0) {
			// TODO: Delete the account
			return true;
		} else {
			Double tempBalance = account.getBalance();
			Double tempDebt = account.getDebt();

			Account oneAccount = DatabaseGet.getAccounts(IDType.cusID, account.getOwnerID()).get(0);

			if (oneAccount != null && account.getCurrency() == oneAccount.getCurrency()) {
				oneAccount.addBalance(tempBalance);
				oneAccount.addDebt(tempDebt);
				DatabaseSet.setAccount(oneAccount);
				// TODO: Delete the account
				return true;
			} else if (oneAccount != null && Currencies.isCurrencyConversionEnabled()) {
				Double cur = Currencies.changeCurrency(account.getCurrency(), oneAccount.getCurrency());
				oneAccount.addBalance(tempBalance*cur);
				oneAccount.addDebt(tempBalance*cur);
				DatabaseSet.setAccount(oneAccount);
				// TODO: Delete the account.
				return true;
			}
		}
		return false;
	}
	
	public Boolean deleteCustomer(CustomerInf customer) {

		ArrayList<Account> accounts =  DatabaseGet.getAccounts(IDType.cusID, customer.getID());
		Boolean accountDeleted = true;
		for (int i = 0; i < accounts.size(); i++) {
			accountDeleted = deleteAccount(accounts.get(i));
			if(!accountDeleted) {
				return false;
			}
		}
		// TODO: Delete the customer
		return true;
	}
	
	public void ChangePassword(EmployeeInf employee, String password) {
		employee.setHash(Hash.SHA512(password, employee.getSalt()));
		DatabaseSet.setEmployee(employee);
	}
}
