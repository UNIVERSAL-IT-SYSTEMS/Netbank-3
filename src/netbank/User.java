package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
	
	public static String getName(CustomerInf customer) { return customer.getName(); }
	public static String getAddress(CustomerInf customer) { return customer.getAddress(); }
	public static String getLanguage(CustomerInf customer) { return customer.getLanguage(); }
	public static String getCountry(CustomerInf customer) { return customer.getCountry(); }
	public static Locale getLocale(CustomerInf customer) { return customer.getLocale(); }
	public static UUID getID(CustomerInf customer) { return customer.getID(); }

	public static Double getAccountBalance(Account account) { return account.getBalance(); }
	public static Double getAccountInterest(Account account) { return account.getInterest(); }
	public static Double getAccountDebt(Account account) { return account.getDebt(); }
	public static UUID getAccountOwnerID(Account account) { return account.getOwnerID(); }
	public static Currency getAccountCurrency(Account account) { return account.getCurrency(); }
	public static UUID getAccountID(Account account) { return account.getAccountID(); }
	
}
