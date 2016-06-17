package netbank;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Account {

	private BigDecimal balance;
	private UUID ownerID;
	private UUID accountID;
	private BigDecimal interest;
	private BigDecimal debt;
	private Currency currency;

	public Account(UUID accountID, UUID ownerID, Double balance, Double interest, Double debt, Currency currency) {
		this.balance = BigDecimal.valueOf(balance).setScale(2, BigDecimal.ROUND_FLOOR);
		this.ownerID = ownerID;
		this.interest = BigDecimal.valueOf(interest).setScale(2, BigDecimal.ROUND_FLOOR);
		this.debt = BigDecimal.valueOf(debt).setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
		this.accountID = accountID;
	}

	public Double getBalance() {
		return balance.doubleValue();
	}

	public Double getInterest() {
		return interest.doubleValue();
	}

	public Double getDebt() {
		return debt.doubleValue();
	}

	public UUID getOwnerID() {
		return ownerID;
	}

	public Currency getCurrency() {
		return currency;
	}

	public UUID getAccountID() {
		return accountID;
	}

	public void addBalance(Double value) {
		balance = balance.add(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void subtractBalance(Double value) {
		balance = balance.subtract(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void addDebt(Double value) {
		debt = debt.add(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void subtractDebt(Double value) {
		debt = debt.subtract(BigDecimal.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void setInterest(Double value) {
		interest = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void setOwnerID(UUID newOwner) {
		ownerID = newOwner;
	}

	public void setCurrency(Currency newCurrency) {
		Double tempBalance = balance.doubleValue();
		tempBalance = tempBalance * Currencies.changeCurrency(currency, newCurrency);
		balance = BigDecimal.valueOf(tempBalance).setScale(2, BigDecimal.ROUND_FLOOR);
		Double tempDebt = debt.doubleValue();
		tempDebt = tempDebt * Currencies.changeCurrency(currency, newCurrency);
		debt = BigDecimal.valueOf(tempDebt).setScale(2, BigDecimal.ROUND_FLOOR);
		currency = newCurrency;
	}

	public boolean belowZeroBalance(Double value) {
		if ((balance.doubleValue() - value) >= 0) {
			return false;
		}
		return true;
	}
	
	public boolean belowZeroDebt(Double value) {
		if ((debt.doubleValue() - value) >= 0) {
			return false;
		}
		return true;
	}
}