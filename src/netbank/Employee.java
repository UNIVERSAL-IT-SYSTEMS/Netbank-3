package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class Employee extends User {
	
	CustomerInf person;
	Account account;
	
	public void setCostumerName(String name) { person.setName(name); }
	public void setCostumerAddress(String address) { person.setAddress(address); }
	public void setCostumerLocation(String language, String region) { 
		person.setLocale(new Locale.Builder().setLanguage(language).setRegion(region).build());
	}
	
	public void setAccountBalance(Double balance) { account.setBalance(balance); }
	public void setAccountInterest(Double interest) { account.setInterest(interest); }
	public void setAccountDebt(Double debt) { account.setDebt(debt); }
	public void setAccountCurrency(Currency currency) { 
		setAccountBalance(changeCurrency(account.getCurrency(),currency)*account.getBalance()); 
		setAccountDebt(changeCurrency(account.getCurrency(),currency)*account.getDebt());
		setAccountCurrency(currency);
	}
	
	public void Deposit(Double amount) {
		account.setBalance(account.getBalance()+amount);
	}
	
	public void changeOwnerOfAccount(UUID newOwner, UUID account) {
		
		// TODO: get person and account from the UUID
		Account thisAccount = null;
		thisAccount.setOwnerID(newOwner);
	}
	
	public Boolean deleteAccount(Account thisAccount) {
		Double tempBalance = account.getBalance();
		Double tempDebt = account.getDebt();
		if( /* TODO: there is at least another account */ tempBalance == 0) {
			
			// TODO: Find another account with same ownerID
			Account oneAccount = null;
			
			oneAccount.setBalance(oneAccount.getBalance()+tempBalance);
			oneAccount.setDebt(oneAccount.getDebt()+tempDebt);
			return true;
		} else if (tempBalance == 0 && tempDebt == 0) {
			return true;
		}
		return false;
	}
}
