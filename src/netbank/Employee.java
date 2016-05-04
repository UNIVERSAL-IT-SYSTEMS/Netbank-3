package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class Employee extends User {
	
	public void setCostumerName(CustomerInf customer, String name) { customer.setName(name); }
	public void setCostumerAddress(CustomerInf customer, String address) { customer.setAddress(address); }
	public void setCostumerLocation(CustomerInf customer, String language, String region) { 
		customer.setLocale(new Locale.Builder().setLanguage(language).setRegion(region).build());
	}
	
	public void setAccountInterest(Account account, Double interest) { account.setInterest(interest); }
	public Boolean setAccountCurrency(Account account, Currency currency) { return account.setCurrency(currency); }
	public void subtractAccountBalance(Account account, Double value) { 
		new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
			null, null, TransactionType.Withdrawal);
		account.subtractBalance(value); 
	}
	public void addAccountDebt(Account account, Double value) { new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
			null, null, TransactionType.AddDebt);
		account.addDebt(value);
	}
	public void subtractAccpountDebt(Account account, Double value) { 
		new Transaction(value, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.SubtractDebt);
		account.subtractDebt(value); 
	}
	public void deposit(Account account, Double amount) {
		new Transaction(amount, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.Deposit);
		account.addBalance(amount);
	}
	
	public void changeOwnerOfAccount(Account account, UUID newOwner, UUID newAccountID) {
		
		// TODO: get person and account from the UUID
		Account thisAccount = null;
		CustomerInf thisCustomer = null;
		thisAccount.setOwnerID(thisCustomer.getID());
		thisAccount.setOwner(thisCustomer.getName());
	}
	
	public Boolean deleteAccount(Account account, Account thisAccount) {
		Double tempBalance = account.getBalance();
		Double tempDebt = account.getDebt();
		if (tempBalance == 0 && tempDebt == 0) {
			return true;
		} else if( /* TODO: there is at least another account */ tempBalance == 0) {
			// TODO: Find another account with same ownerID
			Account oneAccount = null;
			if(account.getCurrency()==thisAccount.getCurrency()) {
				oneAccount.addBalance(tempBalance);
				oneAccount.addDebt(tempDebt);
				return true;
			} else if(Currencies.isCurrencyConversionEnabled()) {
				Double cur = Currencies.changeCurrency(account.getCurrency(), thisAccount.getCurrency());
				oneAccount.addBalance(tempBalance*cur);
				oneAccount.addDebt(tempBalance*cur);
				return true;
			}
		}
		return false;
	}
}
