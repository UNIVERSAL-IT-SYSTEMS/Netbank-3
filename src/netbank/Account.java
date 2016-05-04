package netbank;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;


public class Account {
	
	private BigDecimal balance;
	private String owner;
	private UUID ownerID;
	private UUID accountID;
	private BigDecimal interest;
	private BigDecimal debt;
	private Currency currency;
	
	public Account(Double balance, String owner, UUID ownerID, Double interest, Double debt, Currency currency) {
		this.balance = BigDecimal.valueOf(balance).setScale(2, BigDecimal.ROUND_FLOOR);
		this.owner = owner;
		this.ownerID = ownerID;
		this.interest = BigDecimal.valueOf(interest).setScale(2, BigDecimal.ROUND_FLOOR);
		this.debt = BigDecimal.valueOf(debt).setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
		accountID = UUID.randomUUID();
	}
	
	public Double getBalance() { return balance.doubleValue(); }
	public Double getInterest() { return interest.doubleValue(); }
	public Double getDebt() { return debt.doubleValue(); }
	public UUID getOwnerID() { return ownerID; }
	public String getOwner() { return owner; }
	public Currency getCurrency() { return currency; }
	public UUID getAccountID() { return accountID; }
	
	public void addBalance(Double value) { balance = balance.add(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR); }
	public void subtractBalance(Double value) { balance = balance.subtract(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR); }
	public void addDebt(Double value) { debt = debt.add(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR); }
	public void subtractDebt(Double value) { debt = debt.subtract(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR); }
	public void setInterest(Double value) { interest = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_FLOOR); }
	public Boolean setCurrency(Currency newCurrency) {
		if(Currencies.isCurrencyConversionEnabled()) {
			currency = newCurrency; 
			balance = balance.multiply(BigDecimal.valueOf(Currencies.changeCurrency(currency, newCurrency))).setScale(2, BigDecimal.ROUND_FLOOR);
			debt = debt.multiply(BigDecimal.valueOf(Currencies.changeCurrency(currency, newCurrency))).setScale(2, BigDecimal.ROUND_FLOOR);
			return true;
		}
		return false;
	}
	public void setOwnerID(UUID newOwner) { ownerID = newOwner; }
	public void setOwner(String owner) { this.owner = owner; }
	
	public Boolean belowZero(Double value) { if((balance.doubleValue()-value) >= 0) { return true; } return false; } }
