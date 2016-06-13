package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
	
	public static String getName(UserInf customer) { return customer.getName(); }
	public static String getAddress(UserInf customer) { return customer.getAddress(); }
	public static String getLanguage(UserInf customer) { return customer.getLanguage(); }
	public static String getCountry(UserInf customer) { return customer.getCountry(); }
	public static Locale getLocale(UserInf customer) { return customer.getLocale(); }
	public static UUID getID(UserInf customer) { return customer.getID(); }

	public static Double getAccountBalance(Account account) { return account.getBalance(); }
	public static Double getAccountInterest(Account account) { return account.getInterest(); }
	public static Double getAccountDebt(Account account) { return account.getDebt(); }
	public static UUID getAccountOwnerID(Account account) { return account.getOwnerID(); }
	public static Currency getAccountCurrency(Account account) { return account.getCurrency(); }
	public static UUID getAccountID(Account account) { return account.getAccountID(); }
	
	public static void ChangePassword(UserInf user, String password) {
		user.setHash(Hash.SHA512(password, user.getSalt()));
		DatabaseSet.setUser(user);
	}
}
