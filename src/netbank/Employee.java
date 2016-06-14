package netbank;

import java.util.Calendar;
import java.util.Currency;
import java.util.UUID;
import java.sql.Timestamp;

public class Employee extends User {
	
	public static Boolean newAccount(UserInf user, Double interest, Currency currency) {
		return DatabaseSet.setAccount(new Account(UUID.randomUUID(), user.getID(), 0.0, interest, 0.0, currency));
	}
	
	public static Boolean setAccountInterest(Account account, Double interest) { 
		account.setInterest(interest); 
		return DatabaseSet.setAccount(account);
	}
	
	public static Boolean setAccountCurrency(Account account, Currency currency) { 
		account.setCurrency(currency);
		return DatabaseSet.setAccount(account);
	}
	
	public static Boolean subtractAccountBalance(Account account, Double value) { 
		Boolean setBoth;
		if(account.belowZero(value)) {
			return false;
		}
		setBoth = DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
			account.getAccountID(), value, account.getCurrency(), TransactionType.WITHDRAWAL, 
			new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.subtractBalance(value); 
		setBoth = DatabaseSet.setAccount(account);
		return setBoth;
	}
	
	public static Boolean addAccountDebt(Account account, Double value) { 
		Boolean setBoth;
		setBoth = DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
			account.getAccountID(), value, account.getCurrency(), TransactionType.ADDDEBT, 
			new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.addDebt(value);
		setBoth = DatabaseSet.setAccount(account);
		return setBoth;
	}
	
	public static Boolean subtractAccountDebt(Account account, Double value) {
		Boolean setBoth;
		if(account.belowZero(value)) {
			return false;
		}
		setBoth = DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
			account.getAccountID(), value, account.getCurrency(), TransactionType.SUBTRACTDEBT, 
			new Timestamp(Calendar.getInstance().getTime().getTime())));
		
		account.subtractDebt(value);
		setBoth = DatabaseSet.setAccount(account);
		return setBoth;
	}
	
	public static Boolean deposit(Account account, Double amount) {
		System.out.println("making transaction");
		DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), 
			account.getAccountID(), amount, account.getCurrency(), TransactionType.DEPOSIT, 
			new Timestamp(Calendar.getInstance().getTime().getTime())));
		account.addBalance(amount);
		System.out.println("Hello "+amount);
		return DatabaseSet.setAccount(account);
	}
	
	public static Boolean changeOwnershipOfAccount(Account account, UUID newOwner) {
		UserInf thisCustomer = DatabaseGet.getUserByUserID(newOwner);
		account.setOwnerID(thisCustomer.getID());
		return DatabaseSet.setAccount(account);
	}
	
	public static Boolean deleteAccount(Account account) {
		if (account.getBalance() == 0 && account.getDebt() == 0) {
			DatabaseSet.removeAccount(account);
			return true;
		} else {
			Double tempBalance = account.getBalance();
			Double tempDebt = account.getDebt();

			Account oneAccount = DatabaseGet.getAccountByAccountID(account.getOwnerID());

			if (oneAccount != null && account.getCurrency() == oneAccount.getCurrency()) {
				oneAccount.addBalance(tempBalance);
				oneAccount.addDebt(tempDebt);
				DatabaseSet.setAccount(oneAccount);
				DatabaseSet.removeAccount(account);
				return true;
			} else if (oneAccount != null) {
				Double cur = Currencies.changeCurrency(account.getCurrency(), oneAccount.getCurrency());
				oneAccount.addBalance(tempBalance*cur);
				oneAccount.addDebt(tempBalance*cur);
				DatabaseSet.setAccount(oneAccount);
				DatabaseSet.removeAccount(account);
				return true;
			}
		}
		return false;
	}
}
