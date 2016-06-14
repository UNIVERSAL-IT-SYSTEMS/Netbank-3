package netbank;

import java.util.Calendar;
import java.util.Currency;
import java.util.UUID;
import java.sql.Timestamp;

public class Employee extends User {

	public static Boolean newAccount(UUID userID, Double interest, Currency currency) {
		return DatabaseSet.setAccount(new Account(UUID.randomUUID(), userID, 0.0, interest, 0.0, currency));
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
		if (account.belowZeroBalance(value) || value < 0) {
			return false;
		}
		if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
				account.getAccountID(), value, account.getCurrency(), TransactionType.WITHDRAWAL,
				new Timestamp(Calendar.getInstance().getTime().getTime())))) {
			return false;
		}

		account.subtractBalance(value);
		return DatabaseSet.setAccount(account);
	}

	public static Boolean addAccountDebt(Account account, Double value) {
		if(value < 0) {
			return false;
		}
		if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
				account.getAccountID(), value, account.getCurrency(), TransactionType.ADDDEBT,
				new Timestamp(Calendar.getInstance().getTime().getTime())))) {
			return false;
		}

		account.addDebt(value);
		return DatabaseSet.setAccount(account);
	}

	public static Boolean subtractAccountDebt(Account account, Double value) {
		System.out.println(value < 0);
		if (account.belowZeroDebt(value) || value < 0) {
			return false;
		}
		System.out.println("here");
		if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(),
				account.getAccountID(), value, account.getCurrency(), TransactionType.SUBTRACTDEBT,
				new Timestamp(Calendar.getInstance().getTime().getTime())))) {
			return false;
		}
		
		System.out.println("hesdfre");
		account.subtractDebt(value);
		return DatabaseSet.setAccount(account);
	}

	public static Boolean deposit(Account account, Double amount) {
		if(amount < 0) { return false; }
		if(!DatabaseSet.setTransaction(new Transaction(UUID.randomUUID(), account.getAccountID(), account.getAccountID(),
				amount, account.getCurrency(), TransactionType.DEPOSIT,
				new Timestamp(Calendar.getInstance().getTime().getTime())))) {
			return false;
		}
		account.addBalance(amount);
		return DatabaseSet.setAccount(account);
	}

	public static Boolean changeOwnershipOfAccount(Account account, UUID newOwner) {
		UserInf thisCustomer = DatabaseGet.getUserByUserID(newOwner);
		account.setOwnerID(thisCustomer.getID());
		return DatabaseSet.setAccount(account);
	}

	public static Boolean deleteAccount(Account account) {
		if (account.getBalance() == 0 && account.getDebt() == 0) {
			return DatabaseSet.removeAccount(account);
		} else {
			Double tempBalance = account.getBalance();
			Double tempDebt = account.getDebt();
			Account oneAccount = DatabaseGet.getAccountByAccountID(account.getAccountID());
			if (oneAccount != null && account.getCurrency() == oneAccount.getCurrency()) {
				oneAccount.addBalance(tempBalance);
				oneAccount.addDebt(tempDebt);
				if(!DatabaseSet.setAccount(oneAccount)) { return false; }
				return DatabaseSet.removeAccount(account);
			} else if (oneAccount != null) {
				Double cur = Currencies.changeCurrency(account.getCurrency(), oneAccount.getCurrency());
				oneAccount.addBalance(tempBalance * cur);
				oneAccount.addDebt(tempBalance * cur);
				if(!DatabaseSet.setAccount(oneAccount)) { return false; }
				return DatabaseSet.removeAccount(account);
			}
		}
		return false;
	}
}
