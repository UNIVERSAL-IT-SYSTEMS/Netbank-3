package netbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;


public class Account {
	
	private BigDecimal balance;
	private String owner;
	private UUID ownerID;
	private BigDecimal interest;
	private BigDecimal debt;
	private Currency currency;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public Account(Double balance, String owner, UUID ownerID, Double interest, Double debt, Currency currency) {
		this.balance = BigDecimal.valueOf(balance).setScale(2, BigDecimal.ROUND_FLOOR);
		this.owner = owner;
		this.ownerID = ownerID;
		this.interest = BigDecimal.valueOf(interest).setScale(2, BigDecimal.ROUND_FLOOR);
		this.debt = BigDecimal.valueOf(debt).setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
	}
	
	public Double getBalance() { return balance.doubleValue(); }
	public Double getInterest() { return interest.doubleValue(); }
	public Double getDebt() { return debt.doubleValue(); }
	public Currency getCurrency() { return currency; }
	
	public Boolean setBalance(Double value) { if(value >= 0) { balance = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_FLOOR); return true; } return false; }
	public Boolean setDebt(Double value) { if(value >= 0) { debt = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_FLOOR); return true; } return false; }
	public Boolean setInterest(Double value) { if(value >= 0) { interest = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_FLOOR); return true; } return false; }
	public void setCurrency(Currency newCurrency) { currency = newCurrency; }
}
