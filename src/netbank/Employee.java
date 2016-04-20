package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class Employee extends User {
	
	Person person;
	Account account;
	
	public Employee(Person person) {
		this.person = person;
	}
	
	public void newAccount(Double balance, String owner, Double interest, Double debt, Currency currency) {
		person.addAccount(new Account(balance, owner, UUID.randomUUID(), interest, debt, currency));
	}

	public void setPersonName(String name) { person.setName(name); }
	public void setPersonAddress(String address) { person.setAddress(address); }
	public void setPersonLocation(String language, String region) { 
		person.setLocale(new Locale.Builder().setLanguage(language).setRegion(region).build());
	}
	
	public String getPersonName() { return person.getName(); }
	public String getPersonAddress() { return person.getAddress(); }
	public Locale getPersonLocation() { return person.getLocale(); }
	
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
}
