package netbank;

import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.sql.Timestamp;

public class Employee extends User {
	
	public void setCostumerName(CustomerInf customer, String name) { customer.setName(name); }
	public void setCostumerAddress(CustomerInf customer, String address) { customer.setAddress(address); }
	public void setCostumerLocation(CustomerInf customer, String language, String region) { 
		customer.setLocale(new Locale.Builder().setLanguage(language).setRegion(region).build());
	}
	
	public void setAccountInterest(Account account, Double interest) { account.setInterest(interest); }
	public Boolean setAccountCurrency(Account account, Currency currency) { return account.setCurrency(currency); }
	public Boolean subtractAccountBalance(Account account, Double value) { 
		new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
			null, null, TransactionType.Withdrawal, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		if(account.belowZero(value)) {
			return false;
		}
		account.subtractBalance(value); 
		return true;
	}
	public void addAccountDebt(Account account, Double value) { 
		new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
			null, null, TransactionType.AddDebt, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		account.addDebt(value);
	}
	public Boolean subtractAccpountDebt(Account account, Double value) { 
		new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.SubtractDebt, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		if(account.belowZero(value)) {
			return false;
		}
		account.subtractDebt(value); 
		return true;
	}
	public void deposit(Account account, Double amount) {
		new Transaction(amount, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.Deposit, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		account.addBalance(amount);
	}
	
	public void changeOwnerOfAccount(Account account, UUID newOwner, UUID newAccountID) {
		
		// TODO: get person and account from the UUID
		Account thisAccount = DatabaseGet.getAccount("accID",newAccountID);
		CustomerInf thisCustomer = DatabaseGet.getCustomer("cusID",newOwner);
		thisAccount.setOwnerID(thisCustomer.getID());
		thisAccount.setOwner(thisCustomer.getName());
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
				// TODO: Delete the account
				return true;
			} else if (oneAccount != null && Currencies.isCurrencyConversionEnabled()) {
				Double cur = Currencies.changeCurrency(account.getCurrency(), oneAccount.getCurrency());
				oneAccount.addBalance(tempBalance*cur);
				oneAccount.addDebt(tempBalance*cur);
				// TODO: Delete the account.
				return true;
			}
		}
		return false;
	}
	
	public Boolean deleteCustomer() {
		// TODO: If the customer has an account then return false. Else delete from the database and return true
		return false;
	}
}
