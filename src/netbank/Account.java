package netbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;


public class Account {
	
	BigDecimal balance;
	String owner;
	UUID ownerID;
	BigDecimal interest;
	BigDecimal debt;
	Currency currency;
	List<Transaction> transactions = new ArrayList<Transaction>();
	
	public Account(BigDecimal balance, String owner, UUID ownerID, BigDecimal interest, BigDecimal debt, Currency currency) {
		this.balance = balance.setScale(2, BigDecimal.ROUND_FLOOR);
		this.owner = owner;
		this.ownerID = ownerID;
		this.interest = interest.setScale(2, BigDecimal.ROUND_FLOOR);
		this.debt = debt.setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
	}
	
	
}
