package netbank;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Customer extends User {
	
	public static Boolean transaction(UUID senderID, Account account, Double amount, UUID recieverID) {
			
		Account receiveAccount = DatabaseGet.getAccounts(IDType.ACCID,recieverID).get(0);
		System.out.println(receiveAccount.getCurrency());
		if(receiveAccount == null || account.belowZero(amount) || amount < 0) {
			return false;
		}
		System.out.println("INSERTING TRANSACTION");
		System.out.println(" , "+amount+" , "+new Timestamp(Calendar.getInstance().getTime().getTime()));
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(), 
			receiveAccount.getAccountID(), amount, receiveAccount.getCurrency(), TransactionType.TRANSACTION, 
			new Timestamp(Calendar.getInstance().getTime().getTime())));
		System.out.println("ADJUSTING LOCAL BALANCE");
		account.subtractBalance(amount);
		System.out.println("SETTING ACCOUNT");
		DatabaseSet.setAccount(account);
		System.out.println("here now3");
		if(account.getCurrency() == receiveAccount.getCurrency()) {
			receiveAccount.addBalance(amount);
			DatabaseSet.setAccount(receiveAccount);
			System.out.println(account.getBalance());
			return true;
		} else if (Currencies.isCurrencyConversionEnabled()) {
			receiveAccount.addBalance(amount*Currencies.changeCurrency(account.getCurrency(), receiveAccount.getCurrency()));
			System.out.println(account.getBalance());
			DatabaseSet.setAccount(receiveAccount);
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean withdrawal(CustomerInf customer, Account account, Double amount) {
		if(account.belowZero(amount)) {
			return false;
		}
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getOwnerID(),null, amount, account.getCurrency(), 
			TransactionType.WITHDRAWAL, new Timestamp(Calendar.getInstance().getTime().getTime())));
		account.subtractBalance(amount);
		DatabaseSet.setAccount(account);
		return true;
	}
	
	public static void ChangePassword(CustomerInf customer, String password) {
		customer.setHash(Hash.SHA512(password, customer.getSalt()));
		DatabaseSet.setCostumer(customer);
	}
}
