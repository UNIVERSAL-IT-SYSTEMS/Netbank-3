package netbank;

import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.sql.Timestamp;

public class Employee extends User {
	
	public void newAccount(CustomerInf customer, Double interest, Currency currency) {
		DatabaseSet.setAccount(new Account(0.0, customer.getID(), interest, 0.0, currency, UUID.randomUUID()));
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
		DatabaseSet.setTransaction(new Transaction(value, account.getCurrency(), account.getOwnerID(), 
			null, TransactionType.Withdrawal, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		
		account.subtractBalance(value); 
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public void addAccountDebt(Account account, Double value) { 
		DatabaseSet.setTransaction(new Transaction(value, account.getCurrency(), account.getOwnerID(), 
			null, TransactionType.AddDebt, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		
		account.addDebt(value);
		DatabaseSet.setAccount(account);
	}
	
	public Boolean subtractAccpountDebt(Account account, Double value) { 
		if(account.belowZero(value)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(value, account.getCurrency(), account.getOwnerID(), 
				null, TransactionType.SubtractDebt, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		
		account.subtractDebt(value); 
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public void deposit(Account account, Double amount) {
		DatabaseSet.setTransaction(new Transaction(amount, account.getCurrency(), account.getOwnerID(), 
				null, TransactionType.Deposit, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		account.addBalance(amount);
		DatabaseSet.setAccount(account);
	}
	
	public void changeOwnershipOfAccount(Account account, UUID newOwner) {
		CustomerInf thisCustomer = DatabaseGet.getCustomer("cusID",newOwner);
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
			Account oneAccount = DatabaseGet.getAccount("cusID", account.getOwnerID());
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
		Account account = DatabaseGet.getAccount("cusID", customer.getID());
		Boolean accountDeleted = true;
		while(account != null) {
			accountDeleted = deleteAccount(account);
			if(!accountDeleted) {
				return false;
			}
			account = DatabaseGet.getAccount("cusID", customer.getID());
		}
		
		// TODO: Delete the customer
		return true;
	}
	
	public void ChangePassword(EmployeeInf employee, String password) {
		employee.setHash(Hash.SHA512(password, employee.getSalt()));
		DatabaseSet.setEmployee(employee);
	}
}
