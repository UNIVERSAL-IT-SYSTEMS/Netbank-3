package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {

	public static boolean transaction(UUID senderID, Account account, Double amount, UUID receiverID) {

		if (account.belowZeroBalance(amount) || amount < 0) {
			return false;
		}
		Account receiveAccount = DatabaseGet.getAccountByAccountID(receiverID);
		if(receiveAccount == null) {
			return false;
		} else if(senderID.toString().equals(receiverID.toString())) {
			return true;
		}
		account.subtractBalance(amount);
		if (account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
					receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION,
					new Timestamp(Calendar.getInstance().getTime().getTime())))) {
				return false;
			}
			if(DatabaseSet.setAccount(account)) {
				return DatabaseSet.setAccount(receiveAccount);
			}
		} else {
			Double newAmount = amount * Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency());
			receiveAccount.addBalance(newAmount);
			if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
					receiveAccount.getAccountID(), newAmount, receiveAccount.getCurrency(), TransactionType.TRANSACTION,
					new Timestamp(Calendar.getInstance().getTime().getTime())))) {
				return false;
			}
			if(DatabaseSet.setAccount(account)) {
				return DatabaseSet.setAccount(receiveAccount);
			}
		}
		return false;
	}

	public static boolean withdrawal(Account account, Double amount) {
		if (account.belowZeroBalance(amount) || amount < 0) { 
			return false;
		}
		if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), account.getAccountID(),
				amount, account.getCurrency(), TransactionType.WITHDRAWAL,
				new Timestamp(Calendar.getInstance().getTime().getTime())))) {
			return false;
		}
		account.subtractBalance(amount);
		return DatabaseSet.setAccount(account);
	}

	public static boolean registerUser(String username, String name, String address, String language, String country,
			String password) {
		String salt = Hash.getSalt();
		return DatabaseSet.setUser(new UserInf(UUID.randomUUID(), username, name, address, language, country, salt,
				Hash.SHA512(password, salt), false));
	}
}
