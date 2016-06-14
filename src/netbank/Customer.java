package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {
	
	public static Boolean transaction(UUID senderID, Account account, Double amount, UUID recieverID) {
		
		Account receiveAccount = DatabaseGet.getAccountByAccountID(recieverID);
		System.out.println(receiveAccount.getCurrency());
		if(receiveAccount == null || account.belowZero(amount) || amount < 0 || DatabaseGet.getCurrency(account.getCurrency()) == null) {
			return false;
		}
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		if(account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			DatabaseSet.setAccount(receiveAccount);
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
				receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION, 
				new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		} else {
			Double newAmount = amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency());
			receiveAccount.addBalance(newAmount);
			DatabaseSet.setAccount(receiveAccount);
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
				receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION, 
				new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		}
	}
	
	public static Boolean withdrawal(Account account, Double amount) {
		if(account.belowZero(amount)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
				account.getAccountID(), amount, account.getCurrency(), TransactionType.WITHDRAWAL, 
				new Timestamp(Calendar.getInstance().getTime().getTime())));
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		return true;
	}
}
