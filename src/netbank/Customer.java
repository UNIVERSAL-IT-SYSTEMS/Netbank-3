package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {

	public static Boolean transaction(UUID senderID, Account account, Double amount, UUID receiverID) {

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
		DatabaseSet.setAccount(account);
		
		if (account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			DatabaseSet.setAccount(receiveAccount);
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
					receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION,
					new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		} else {
			Double newAmount = amount * Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency());
			receiveAccount.addBalance(newAmount);
			DatabaseSet.setAccount(receiveAccount);
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
					receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION,
					new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		}
	}

	public static Boolean withdrawal(Account account, Double amount) {
		System.out.println(account.getBalance());
		System.out.println(amount);
		if (account.belowZeroBalance(amount) || amount < 0) { 
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), account.getAccountID(),
				amount, account.getCurrency(), TransactionType.WITHDRAWAL,
				new Timestamp(Calendar.getInstance().getTime().getTime())));
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		return true;
	}
}
