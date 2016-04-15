package netbank;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class Employee extends User {
	
	Person person;
	Account account;
	
	public Employee(Person person) {
		this.person = person;
	}
	
	public void newAccount(BigDecimal balance, String owner, BigDecimal interest, BigDecimal debt, Currency currency) {
		person.accounts.add(new Account(balance, owner, UUID.randomUUID(), interest, debt, currency));
	}

	public void setPersonName(String name) { person.name = name; }
	public void setPersonAddress(String address) { person.address = address; }
	public void setPersonLocation(String language, String region) { 
		person.location = new Locale.Builder().setLanguage(language).setRegion(region).build();
	}
	
	public String getPersonName() { return person.name; }
	public String getPersonAddress() { return person.address; }
	public Locale getPersonLocation() { return person.location; }
	
	public void setAccountBalance(BigDecimal balance) { account.balance = change(balance); }
	public void setAccountOwnerID(UUID ownerID) { account.ownerID = ownerID; }
	public void setAccountInterest(BigDecimal interest) { account.interest = change(interest); }
	public void setAccountDept(BigDecimal debt) { account.debt = change(debt); }
	public void setAccountCurrency(Currency currency) { account.currency = currency; }
	
}
