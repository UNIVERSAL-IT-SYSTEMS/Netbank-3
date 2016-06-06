package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {
	
	public Boolean transaction(Account account, Double amount, UUID recieverID) {
			
		// TODO Receive account.
		Account receiveAccount = DatabaseGet.getAccount("accID",recieverID);
		
		if(account.belowZero(amount)) {
			return false;
		}
		new Transaction(amount, receiveAccount.getCurrency(), account.getOwnerID(), account.getOwner(), 
				receiveAccount.getAccountID(), receiveAccount.getOwner(), TransactionType.Transaction, 
				new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		account.subtractBalance(amount);
		if(account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			// TODO: Save both accounts
			return true;
		} else if (Currencies.isCurrencyConversionEnabled()) {
			receiveAccount.addBalance(amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency()));
			// TODO: Save both accounts
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean withdrawal(Account account, Double amount) {
		if(account.belowZero(amount)) {
			return false;
		}
		new Transaction(amount, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.Withdrawal, 
				new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID());
		account.subtractBalance(amount);
		// TODO: Save account
		return true;
	}
	
}
