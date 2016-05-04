package netbank;

import java.util.UUID;

public class Customer extends User {
	
	public Boolean transaction(Account account, Double amount, UUID recieverId) {
			
		// TODO Receive account.
		 Account receiveAccount = null;
		if(account.belowZero(amount)) {
			return false;
		}
		new Transaction(amount, receiveAccount.getCurrency(), account.getOwnerID(), account.getOwner(), 
				receiveAccount.getAccountID(), receiveAccount.getOwner(), TransactionType.Transaction);
		account.subtractBalance(amount);
		if(account.getCurrency()==receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			return true;
		} else if(Currencies.isCurrencyConversionEnabled()) {
			receiveAccount.addBalance(amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency()));
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
				null, null, TransactionType.Withdrawal);
		account.subtractBalance(amount);
		return true;
	}
	
}
