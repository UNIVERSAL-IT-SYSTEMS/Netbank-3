package netbank;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;

public class Transaction {
	private TransactionType type;
	private Timestamp currentTimestamp;
	private UUID transactionID;
	private UUID sender;
	private UUID receiver;
	private BigDecimal amount;
	private String senderName;
	private String receiverName;
	private Currency currency; // Receiver's currency.
	
	public Transaction(Double amount, Currency currency, UUID sender, String senderName, 
			UUID receiver, String receiverName, TransactionType type, 
			Timestamp currenctTimestamp, UUID transactionID) {
		this.amount = BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_FLOOR);
		this.currency = currency;
		this.sender = sender;
		this.receiver = receiver;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.type = type;
		this.currentTimestamp = currenctTimestamp;
		this.transactionID = transactionID;
	}
	
	public Timestamp getTimestamp() { return currentTimestamp; }
	public Double getAmount() { return amount.doubleValue(); }
	public Currency getCurrency() { return currency; }
	public UUID getTransactionID() { return transactionID; }
	public UUID getSenderID() { return sender; }
	public UUID getReceiverID() { return receiver; }
	public String getSenderName() { return senderName; }
	public String getReceiverName() { return receiverName; }
	public String getTransactionType() { return type.name(); }
	
}
