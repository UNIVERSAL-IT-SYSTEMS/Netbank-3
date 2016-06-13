package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {
	
	public static Boolean transaction(UUID senderID, Account account, Double amount, UUID recieverID) {
		
		Account receiveAccount = DatabaseGet.getAccountByAccountID(recieverID);
		System.out.println(receiveAccount.getCurrency());
		if(receiveAccount == null || account.belowZero(amount) || amount < 0) {
			return false;
		}
		System.out.println("ADJUSTING LOCAL BALANCE");
		account.subtractBalance(amount);
		System.out.println("SETTING ACCOUNT");
		DatabaseSet.setAccount(account);
		System.out.println("here now");
		if(account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			DatabaseSet.setAccount(receiveAccount);
			System.out.println(account.getBalance());
			System.out.println("INSERTING TRANSACTION");
			System.out.println(" , "+amount+" , "+new Timestamp(Calendar.getInstance().getTime().getTime()));
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), 
				receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION, 
				new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		} else if (Currencies.isCurrencyConversionEnabled()) {
			Double newAmount = amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency());
			receiveAccount.addBalance(newAmount);
			System.out.println(account.getBalance());
			DatabaseSet.setAccount(receiveAccount);
			System.out.println("INSERTING TRANSACTION");
			System.out.println(" , "+newAmount+" , "+new Timestamp(Calendar.getInstance().getTime().getTime()));
			DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), 
				receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION, 
				new Timestamp(Calendar.getInstance().getTime().getTime())));
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean withdrawal(Account account, Double amount) {
		System.out.println(amount);
		System.out.println(account.getBalance());
		if(account.belowZero(amount)) {
			return false;
		}
		System.out.println("Not below zero");
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(),null, amount, account.getCurrency(), 
			TransactionType.WITHDRAWAL, new Timestamp(Calendar.getInstance().getTime().getTime())));
		System.out.println("Transaction made");
		account.subtractBalance(amount);
		System.out.println("subracted");
		DatabaseSet.setAccount(account);
		System.out.println("set");
		return true;
	}
}
