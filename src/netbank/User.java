package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
		
	public String getName(CustomerInf customer) { return customer.getName(); }
	public String getAddress(CustomerInf customer) { return customer.getAddress(); }
	public Locale getLocale(CustomerInf customer) { return customer.getLocale(); }
	public UUID getID(CustomerInf customer) { return customer.getID(); }

	public double getAccountBalance(Account account) { return account.getBalance(); }
	public double getAccountInterest(Account account) { return account.getInterest(); }
	public double getAccountDebt(Account account) { return account.getDebt(); }
	public UUID getAccountOwnerID(Account account) { return account.getOwnerID(); }
	public String getAccountOwner(Account account) { return account.getOwner(); }
	public Currency getAccountCurrency(Account account) { return account.getCurrency(); }
	public UUID getAccountID(Account account) { return account.getAccountID(); }
	
}
