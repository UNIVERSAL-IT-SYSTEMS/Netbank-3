package netbank;

import java.io.IOException;
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
	
	public void setAccountInterest(Double interest) { account.setInterest(interest); }
	public void setAccountCurrency(Currency currency) { setAccountCurrency(currency); }
	
	public void Deposit(Double amount) {
		account.addBalance(account.getBalance()+amount);
	}
	
	public void changeOwnerOfAccount(UUID newOwner, UUID account) {
		
		// TODO: get person and account from the UUID
		Account thisAccount = null;
		CustomerInf thisCustomer = null;
		thisAccount.setOwnerID(thisCustomer.getID());
		thisAccount.setOwner(thisCustomer.getName());
	}
	
	public Boolean deleteAccount(Account thisAccount) {
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
			} else {
				Double cur = Currencies.changeCurrency(account.getCurrency(), thisAccount.getCurrency());
				oneAccount.addBalance((tempBalance*cur));
				oneAccount.addDebt((tempBalance*cur));
				return true;
			}
		}
		return false;
	}
}
