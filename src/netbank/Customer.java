package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {
	
	public Boolean transaction(Account account, Double amount, UUID recieverID) {
			
		Account receiveAccount = DatabaseGet.getAccount("accID",recieverID);
		if(receiveAccount == null || account.belowZero(amount)) {
			return false;
		}
		
		DatabaseSet.setTransaction(new Transaction(amount, receiveAccount.getCurrency(), account.getOwnerID(), account.getOwner(), 
				receiveAccount.getAccountID(), receiveAccount.getOwner(), TransactionType.Transaction, 
				new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		if(account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			DatabaseSet.setAccount(receiveAccount);
			return true;
		} else if (Currencies.isCurrencyConversionEnabled()) {
			receiveAccount.addBalance(amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency()));
			DatabaseSet.setAccount(receiveAccount);
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean withdrawal(Account account, Double amount) {
		if(account.belowZero(amount)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(amount, account.getCurrency(), account.getOwnerID(), account.getOwner(), 
				null, null, TransactionType.Withdrawal, new Timestamp(Calendar.getInstance().getTime().getTime()), UUID.randomUUID()));
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public void ChangePassword(CustomerInf customer, String password) {
		customer.setHash(Hash.SHA512(password, customer.getSalt()));
		DatabaseSet.setCostumer(customer);
	}
}
