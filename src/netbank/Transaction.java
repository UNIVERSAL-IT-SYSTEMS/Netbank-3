package netbank;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class Transaction {
	private Timestamp currentTimestamp;
	private UUID transactionID;
	private UUID sender;
	private UUID receiver;
	private BigDecimal amount;
	private String senderName;
	private String receiverName;
	
	public Transaction(Double amount, UUID sender, String senderName, UUID receiver, String receiverName) {
		this.amount = BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_FLOOR);
		this.sender = sender;
		this.receiver = receiver;
		this.senderName = senderName;
		this.receiverName = receiverName;
		currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		transactionID = UUID.randomUUID();
	}
	
	public Timestamp getTimestamp() { return currentTimestamp; }
	public Double getAmount() { return amount.doubleValue(); }
	public UUID getTransactionID() { return transactionID; }
	public UUID getSenderID() { return sender; }
	public UUID getReceiverID() { return receiver; }
	public String getSenderName() { return senderName; }
	public String getReceiverName() { return receiverName; }
	
}
