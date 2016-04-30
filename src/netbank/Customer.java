package netbank;

import java.util.UUID;

public class Customer extends User {

	CustomerInf person;
	Account account;
	
	public Boolean transaction(Double amount, UUID recieverId) {
			
		// TODO Receive account.
		
		if(account.belowZero(amount)) {
			return false;
		}
		
		Account receiveAccount = null;
		Transaction trans = new Transaction(amount, receiveAccount.getCurrency(), account.getOwnerID(), account.getOwner(), 
											receiveAccount.getAccountID(), receiveAccount.getOwner());
		account.subtractBalance(amount);
		if(account.getCurrency()==receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
		} else {
			receiveAccount.addBalance(amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency()));
		}
		
		return true;
	}
	
	public Boolean withdrawal(Double amount) {
		if(account.belowZero(amount)) {
			return false;
		}
		account.subtractBalance(amount);
		return true;
	}
	
}
