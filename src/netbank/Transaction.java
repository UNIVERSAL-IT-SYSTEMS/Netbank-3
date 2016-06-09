package netbank;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;

public class Transaction {
	private TransactionType type;
	private long currentTimestamp;
	private UUID transactionID;
	private UUID sender;
	private UUID receiver;
	private BigDecimal amount;
	private Currency currency; // Receiver's currency.
	
	public Transaction(UUID transactionID, UUID sender, UUID receiver, Double amount, Currency currency,  
			TransactionType type, long currenctTimestamp) {
		this.amount = BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		this.currentTimestamp = currenctTimestamp;
		this.transactionID = transactionID;
	}
	
	public long getTimestamp() { return currentTimestamp; }
	public Double getAmount() { return amount.doubleValue(); }
	public Currency getCurrency() { return currency; }
	public UUID getTransactionID() { return transactionID; }
	public UUID getSenderID() { return sender; }
	public UUID getReceiverID() { return receiver; }
	public TransactionType getTransactionType() { return type; }
	
}
