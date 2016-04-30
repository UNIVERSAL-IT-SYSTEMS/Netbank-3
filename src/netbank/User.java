package netbank;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public abstract class User {
	
	Account account;
	CustomerInf costumer;
	
	public String getName() { return costumer.getName(); }
	public String getAddress() { return costumer.getAddress(); }
	public Locale getLocale() { return costumer.getLocale(); }
	public UUID getID() { return costumer.getID(); }
	public String getSalt() { return costumer.getSalt(); }
	public String getHash() { return costumer.getHash(); }

	public Double getBalance() { return account.getBalance(); }
	public Double getInterest() { return account.getInterest(); }
	public Double getDebt() { return account.getDebt(); }
	public UUID getOwnerID() { return account.getOwnerID(); }
	public String getOwner() { return account.getOwner(); }
	public Currency getCurrency() { return account.getCurrency(); }
	public UUID getAccountID() { return account.getAccountID(); }
	
}
