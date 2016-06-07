package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
	
	public String getName(CustomerInf customer) { return customer.getName(); }
	public String getAddress(CustomerInf customer) { return customer.getAddress(); }
	public String getLanguage(CustomerInf customer) { return customer.getLanguage(); }
	public String getCountry(CustomerInf customer) { return customer.getCountry(); }
	public Locale getLocale(CustomerInf customer) { return customer.getLocale(); }
	public UUID getID(CustomerInf customer) { return customer.getID(); }

	public Double getAccountBalance(Account account) { return account.getBalance(); }
	public Double getAccountInterest(Account account) { return account.getInterest(); }
	public Double getAccountDebt(Account account) { return account.getDebt(); }
	public UUID getAccountOwnerID(Account account) { return account.getOwnerID(); }
	public Currency getAccountCurrency(Account account) { return account.getCurrency(); }
	public UUID getAccountID(Account account) { return account.getAccountID(); }
	
}
