package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
	
	public static String getName(UserInf user) { return user.getName(); }
	public static String getAddress(UserInf user) { return user.getAddress(); }
	public static String getLanguage(UserInf user) { return user.getLanguage(); }
	public static String getCountry(UserInf user) { return user.getCountry(); }
	public static Locale getLocale(UserInf user) { return user.getLocale(); }
	public static UUID getID(UserInf user) { return user.getID(); }

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
